import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import * as THREE from 'three';
import {UserInterface} from "./user-interface";
import {Statistics} from "./statistics";
import {Utils} from "./utils";
import {ModelLoader} from "./model-loader";
import {TextureLoader} from "./texture-loader";
import {Cameras} from "./cameras";
import {Lights} from "./lights";
import Truck from "./truck";
import {Warehouses} from "./warehouses";
import {Roads} from "./roads";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {WarehouseService} from "../../../services/warehouse.service";
import {fogData, roadData, truckData} from "./default-data";
import {Color, Object3D, SpotLight, Vector3} from "three";
import {AudioLoader} from "./audio-loader";
import {degToRad} from "three/src/math/MathUtils";
import { GlobalService } from 'src/services/global.service';

@Component({
    selector: 'app-map',
    templateUrl: './map.component.html',
    styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
    warehouses: IWarehouseDto[] = [];

    private renderer: THREE.WebGLRenderer | undefined
    private scene: THREE.Scene | undefined
    private isDay = true
    private hasFog = false
    private truck = new Truck()
    private clock = new THREE.Clock();

    @ViewChild('renderCanvas', {static: true})
    public renderCanvas!: ElementRef<HTMLCanvasElement>;

    constructor(private warehouseService: WarehouseService, public global: GlobalService) { }

    ngOnDestroy(): void {
        Statistics.stop()
        UserInterface.removeUserInterface()
        this.renderer?.setAnimationLoop(null)
    }

    startAnimation(): void {
        let registeredTime: number | undefined
        let gondomarWarehouse: Object3D | undefined
        // Truck in Gondomar
        if(this.truck.positionedIn == "") {
            gondomarWarehouse = this.scene?.getObjectByName("Gondomar")

            ModelLoader.truckModel.position.set(
                gondomarWarehouse!.position.x + 1,
                gondomarWarehouse!.position.y + roadData.roundaboutHeight + 0.24,
                gondomarWarehouse!.position.z
            )
            ModelLoader.truckModel.rotateY(Math.PI / 2)
            ModelLoader.truckModel.scale.set(truckData.scale,truckData.scale,truckData.scale)
            this.truck.positionedIn = "w"
            this.truck.originWarehouse = gondomarWarehouse!
        }

        let startTime: number
        let i = 1
        let hasArrivedToANewWarehouse = false

        /* Time */
        const animate = () => {
            const dt = this.clock.getDelta();

            const truck = ModelLoader.truckModel;
            this.truck.model = truck;
            this.truck.rightLightModel = this.scene?.getObjectByName("truckRightLight");
            this.truck.leftLightModel = this.scene?.getObjectByName("truckLeftLight");
            this.truck.brakeLightModel = this.scene?.getObjectByName("truckBrakeLight");

            const acceleration = this.truck.acceleration * dt;
            const reversingAcceleration = acceleration / 2;
            const breakDeacceleration = acceleration * 3;
            const normalDeacceleration = acceleration / 2.5;

            if(this.truck.keyStates.space && this.truck.originWarehouse != undefined) {
                truckData.maxTurning = 25
                // Point to next warehouse
                if(this.truck.originWarehouse.name == truckData.automaticMovementWarehouses[i]) {
                    i++
                    hasArrivedToANewWarehouse = true
                }

                const realWorldTruckPosition = truck.getWorldPosition(new Vector3())
                const destinationWarehouse = this.scene?.getObjectByName(truckData.automaticMovementWarehouses[i])
                const connectionToDestination = this.scene?.getObjectByName(this.truck.originWarehouse.name + "-" + truckData.automaticMovementWarehouses[i])

                const rotation = Utils.getOrientation(this.truck.originWarehouse, destinationWarehouse!)
                const truckAngle = Utils.getOrientation(this.truck.originWarehouse, truck)
                const angleDifference =  Math.abs(truckAngle - connectionToDestination!.rotation.y)

                if(startTime == null) {
                    startTime = this.clock.elapsedTime
                    this.truck.speed = this.truck.maxSpeed / 3
                    this.truck.wheelDirection = this.truck.maxTurning * 0.9
                }

                if(this.truck.positionedIn == "r") {
                    this.truck.speed = this.truck.maxSpeed * 3
                } else {
                    this.truck.speed = this.truck.maxSpeed / 3
                }
                if(hasArrivedToANewWarehouse) {
                    if(registeredTime != undefined && this.clock.elapsedTime - registeredTime > 6)
                        hasArrivedToANewWarehouse = false
                    if(Utils.squared2DDistance(this.truck.originWarehouse!.position, realWorldTruckPosition) < Math.pow(roadData.roundaboutRadius*0.59,2)) {
                        this.truck.keyStates.left = true
                        registeredTime = this.clock.elapsedTime
                    } else if(Utils.squared2DDistance(this.truck.originWarehouse!.position, realWorldTruckPosition) < Math.pow(roadData.roundaboutRadius*0.95,2)) {
                        this.truck.wheelDirection = -this.truck.maxTurning * 0.9
                    }
                } else if(angleDifference < 0.15 && this.truck.positionedIn != "r") {
                    if (Math.abs((degToRad(this.truck.direction) + Math.PI / 2) - rotation) < 0.1) {
                        this.truck.direction = rotation - Math.PI / 2
                        this.truck.wheelDirection = 0
                        this.truck.keyStates.left = false
                        this.truck.keyStates.right = false
                    }
                } else if(angleDifference < 0.65 && angleDifference > 0.60 && this.truck.positionedIn != "r") {
                    this.truck.keyStates.left = false
                    this.truck.keyStates.right = true
                }
            }

            if (this.truck.keyStates.backward && !truckData.automaticMovement) {
                (this.truck.brakeLightModel as SpotLight).intensity = 0.75
                if(this.truck.speed > 0) {
                    (this.truck.brakeLightModel as SpotLight).color = new Color(0xff0000)
                    this.truck.speed = this.truck.speed - breakDeacceleration;
                } else if(this.truck.speed > -this.truck.reverseMaxSpeed) {
                    (this.truck.brakeLightModel as SpotLight).color = new Color(0xffffff)

                    if(AudioLoader.reserveSound != null)
                        AudioLoader.reserveSound.play();

                    this.truck.speed = this.truck.speed - reversingAcceleration;
                }
            } else if (this.truck.keyStates.forward && !truckData.automaticMovement) {
                if(AudioLoader.reserveSound != null && AudioLoader.reserveSound.isPlaying)
                    AudioLoader.reserveSound.stop();
                (this.truck.brakeLightModel as SpotLight).intensity = 0
                if(this.truck.speed < 0) {
                    this.truck.speed = this.truck.speed + breakDeacceleration;
                } else if(this.truck.speed < this.truck.maxSpeed) {
                    this.truck.speed = this.truck.speed + acceleration;
                }
            } else if(!truckData.automaticMovement) {
                if(AudioLoader.reserveSound != null && AudioLoader.reserveSound.isPlaying)
                    AudioLoader.reserveSound.stop();
                (this.truck.brakeLightModel as SpotLight).intensity = 0
                if(Math.abs(this.truck.speed) < 0.1) {
                    this.truck.speed = 0
                } else {
                    if(this.truck.slope <= 0) {
                        if(this.truck.speed > 0) {
                            this.truck.speed = this.truck.speed - normalDeacceleration
                        } else {
                            this.truck.speed = this.truck.speed + normalDeacceleration
                        }
                    }
                }
            }

            const yCoveredDistance = 0
            const coveredDistance = this.truck.speed * dt;
            const directionIncrement = this.truck.turningSpeed * dt;

            if (this.truck.keyStates.left) { // The player is turning left
                if(this.truck.wheelDirection <= this.truck.maxTurning)
                    this.truck.wheelDirection += directionIncrement;
            }
            else if (this.truck.keyStates.right) { // The player is turning right
                if(this.truck.wheelDirection >= -this.truck.maxTurning)
                    this.truck.wheelDirection -= directionIncrement;
            }


            if (this.truck.speed < 0) { // The player is moving backward
                this.truck.direction = this.truck.direction + this.truck.wheelDirection*-this.truck.speed*-0.028;
            } else if (this.truck.speed > 0) {
                this.truck.direction = this.truck.direction + this.truck.wheelDirection*this.truck.speed*0.028;
            }

            const direction = THREE.MathUtils.degToRad(this.truck.direction);
            const wheelDirection = THREE.MathUtils.degToRad(this.truck.wheelDirection);

            const newPosition = new THREE.Vector3(
                -coveredDistance * Math.sin(direction),
                -coveredDistance * Math.tan(this.truck.slope),
                -coveredDistance * Math.cos(direction)
            ).add(truck.position);

            // Create a truck clone with the future dimensions and see if it collides
            const truckInNewPosition = truck.clone(true)
            truckInNewPosition.rotation.x = truck.rotation.x
            truckInNewPosition.rotation.y = direction + Math.PI/2
            truckInNewPosition.rotation.z = truck.rotation.z
            truckInNewPosition.position.set(newPosition.x, newPosition.y, newPosition.z )
            truckInNewPosition.updateMatrixWorld();

            if(this.truck.speed != 0) {
                if (!this.truck.collides(this.scene!, truckInNewPosition)) {
                    truck.rotation.y = direction + Math.PI/2
                    truck.position.set(truckInNewPosition.position.x, truckInNewPosition.position.y, truckInNewPosition.position.z)

                    truck.rotation.x = (this.truck.originWarehouse != null && this.truck.destinationWarehouse != null && this.truck.destinationWarehouse.position.y > this.truck.originWarehouse.position.y) ?
                        -this.truck.slope : this.truck.slope
                } else {
                    this.truck.speed = 0
                }
            }

            Truck.rotateWheels(wheelDirection)

            render();
            Statistics.update();
        }

        const render = () => {
            this.renderer?.setViewport(0, 0, UserInterface.getWidth(), UserInterface.getHeight());
            this.renderer?.clear();
            this.renderer?.render(this.scene!, Cameras.orbitCamera);
        }

        this.renderer?.setAnimationLoop(animate)
    }

    ngOnInit(): void {
        this.global.updateData(true);
        const onWindowResize = () => {
            this.renderer?.setSize(UserInterface.getWidth(), UserInterface.getHeight());
        }
        const onKeyChange = (event: KeyboardEvent, pressed: boolean) => {
            if(event.code.startsWith("Digit")) {
                if (pressed) {
                    const d = Number.parseInt(event.code.replace("Digit", ""))
                    const warehouse = this.scene!.getObjectByName(this.warehouses[d].designation) as THREE.Object3D;
                    Cameras.focusIn(warehouse)
                } else {
                    Cameras.focusDefault()
                }
            }
            console.log(event.code)
            if(event.code == "KeyM" && pressed) {
                const backgroundTexture = this.isDay ? TextureLoader.nightSky : TextureLoader.daySky

                const rt = new THREE.WebGLCubeRenderTarget(backgroundTexture.image.height);
                rt.fromEquirectangularTexture(this.renderer!, backgroundTexture);
                this.scene!.background = rt.texture;

                Lights.directLight.intensity = this.isDay ? 0 : 0.7
                this.isDay = !this.isDay
            }
            if(event.code == "KeyF" && pressed) {
                if (!this.hasFog) {
                    const color = fogData.color;  // white
                    const near = fogData.near;
                    const far = fogData.far;
                    this.scene!.fog = new THREE.Fog(color, near, far);
                } else {
                    this.scene!.fog = null
                }
                this.hasFog = !this.hasFog
            }
            if (event.code == truckData.keyCodes.left && !truckData.automaticMovement) {
                this.truck.keyStates.left = pressed;
            }
            else if (event.code == truckData.keyCodes.right && !truckData.automaticMovement) {
                this.truck.keyStates.right = pressed;
            }
            if (event.code == truckData.keyCodes.backward && !truckData.automaticMovement) {
                this.truck.keyStates.backward = pressed;
            }
            else if (event.code == truckData.keyCodes.forward && !truckData.automaticMovement) {
                this.truck.keyStates.forward = pressed;
            }
            else if (event.code == truckData.keyCodes.space) {
                this.truck.keyStates.space = true;
            }
        }
        window.addEventListener("resize", onWindowResize);
        document.addEventListener("keydown", event => onKeyChange(event, true));
        document.addEventListener("keyup", event => onKeyChange(event, false));

        /* Create the Scene */
        this.scene = new THREE.Scene();
        const screenWidth = UserInterface.getWidth();
        const screenHeight = UserInterface.getHeight();

        /* Create the Renderer */
        const renderCanvas = document.getElementById('renderCanvas');
        this.renderer = new THREE.WebGLRenderer({
            canvas: renderCanvas!,
            alpha: false,    // transparent background
            antialias: true // smooth edges
        });
        this.renderer.setSize(screenWidth, screenHeight);
        this.renderer.autoClear = true;
        this.renderer.setPixelRatio(window.devicePixelRatio);
        this.renderer.setSize(window.innerWidth, window.innerHeight);

        /* Create the cameras */
        Cameras.addTo(this.scene, this.renderer, screenWidth, screenHeight)

        /* Create the lights */
        Lights.addTo(this.scene)

        /* Create the statistics */
        Statistics.show();

        /* Create the UI */
        UserInterface.createUserInterface();

        /* Create 3D Visual Guide */
        //VisualGuide.addTo(this.scene)

        /* Load textures and model */
        AudioLoader.loadTo(Cameras.orbitCamera)
        TextureLoader.loadTo(this.scene, this.renderer)
        ModelLoader.loadTo(this.scene, () => { this.getWarehouses()} )

        this.renderer.setSize(screenWidth, screenHeight);
        this.renderer.autoClear = false;
    }

    getWarehouses(): void {
        this.warehouseService.getActiveWarehouses()
            .subscribe(warehouses => this.warehouses = warehouses, null, () => {
                Warehouses.addTo(this.scene!, this.warehouses)
                Roads.addTo(this.scene!, this.warehouses)
                this.startAnimation()
            });
    }
}
