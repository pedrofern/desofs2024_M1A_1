import {GLTFLoader} from 'three/examples/jsm/loaders/GLTFLoader';
import * as THREE from "three";
import * as DefaultData from "./default-data"
import {OBJLoader} from "three/examples/jsm/loaders/OBJLoader";
import {roadData} from "./default-data";
import {MeshBasicMaterial} from "three";

export class ModelLoader {

    static count = 0

    static truckModel: THREE.Object3D;
    static warehouseModel: THREE.Object3D;

    static loadTo(scene: THREE.Scene, callback: any) {
        /* Save your model reference in a variable */
        this.truckModel = new THREE.Object3D();
        this.truckModel.name = "truck";

        /* The truck model */
        new GLTFLoader().load(
            DefaultData.truckData.url,
            function (gltf) {
                ModelLoader.truckModel = gltf.scene;
                ModelLoader.truckModel.scale.set(0.30,0.30,0.30)
                ModelLoader.truckModel.receiveShadow = true

                const rightLight = new THREE.SpotLight( 0xffffff,1, 10);
                rightLight.name = "truckRightLight";
                rightLight.position.y -= 83
                rightLight.position.x += 125
                rightLight.position.z += 31

                rightLight.castShadow = true;

                rightLight.shadow.mapSize.width = 256;
                rightLight.shadow.mapSize.height = 256;

                rightLight.shadow.camera.near = 10;
                rightLight.shadow.camera.far = 20;
                rightLight.shadow.camera.fov = 5;

                rightLight.target.position.set(
                    rightLight.position.x + 1,
                    rightLight.position.y - 0.6,
                    rightLight.position.z
                )

                rightLight.target.updateMatrixWorld();

                ModelLoader.truckModel.add(rightLight);
                ModelLoader.truckModel.add(rightLight.target);

                const leftLight = rightLight.clone();
                leftLight.name = "truckLeftLight";
                leftLight.position.z = -31

                leftLight.target.position.set(
                    leftLight.position.x + 1,
                    leftLight.position.y - 0.6,
                    leftLight.position.z
                )

                leftLight.target.updateMatrixWorld();

                ModelLoader.truckModel.add(leftLight);
                ModelLoader.truckModel.add(leftLight.target);

                const geometry3 = new THREE.BoxGeometry(0.1, 0.1, 0.1);
                const road = new THREE.Mesh( geometry3, new MeshBasicMaterial());
                road.name = "truckFront";
                road.visible = false
                road.position.y -= 83
                road.position.x += 125
                road.position.z = 0

                ModelLoader.truckModel.add(road);

                const brakeLight = new THREE.SpotLight( 0xff0000,0, 5, Math.PI / 4);
                brakeLight.name = "truckBrakeLight";
                brakeLight.position.z = 0
                brakeLight.position.y = -83
                brakeLight.position.x = -115

                brakeLight.castShadow = true;

                brakeLight.shadow.mapSize.width = 256;
                brakeLight.shadow.mapSize.height = 256;

                brakeLight.shadow.camera.near = 10;
                brakeLight.shadow.camera.far = 20;
                brakeLight.shadow.camera.fov = 5;

                brakeLight.target.position.set(
                    brakeLight.position.x - 1,
                    brakeLight.position.y - 0.6,
                    brakeLight.position.z
                )

                brakeLight.target.updateMatrixWorld();

                ModelLoader.truckModel.add(brakeLight);
                ModelLoader.truckModel.add(brakeLight.target);

                //const spotLightHelper2 = new THREE.SpotLightHelper( rightLight );
                //scene.add( spotLightHelper2 );

                // Add GLTF model to scene
                scene.add(ModelLoader.truckModel);
                ModelLoader.count++

                if(ModelLoader.count == 2)
                    callback()
            },
            undefined,
            function (error) {
                console.error(error);
            }
        );

        /* The warehouse model */
        new GLTFLoader().load(
            DefaultData.warehouseData.url,
            function (gltf) {
                ModelLoader.warehouseModel = gltf.scene;
                ModelLoader.warehouseModel.name = "warehouse";
                ModelLoader.warehouseModel.scale.set(0.003,0.003,0.003)
                ModelLoader.warehouseModel.translateY(0.1)
                ModelLoader.warehouseModel.translateX(4.5)
                ModelLoader.warehouseModel.translateZ(4.3)
                ModelLoader.warehouseModel.rotateY(-Math.PI / 2);
                ModelLoader.warehouseModel.castShadow = true
                ModelLoader.warehouseModel.receiveShadow = true

                ModelLoader.count++

                if(ModelLoader.count == 2)
                    callback()
            },
            undefined,
            function (error) {
                console.error(error);
            }
        );
    }
}
