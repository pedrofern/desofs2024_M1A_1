import {roadData, truckData} from "./default-data";
import {MathUtils, Object3D, Scene, Vector3} from "three";
import {ModelLoader} from "./model-loader";
import {Roads} from "./roads";
import {Utils} from "./utils";

export default class Truck {

    wheelDirection = 0
    speed = 0
    slope = 0
    acceleration = truckData.acceleration
    normalDeaccelerationFactor = truckData.normalDeaccelerationFactor
    direction = truckData.initialDirection
    maxSpeed = truckData.maxSpeed
    reverseMaxSpeed = truckData.reverseMaxSpeed
    turningSpeed = truckData.turningSpeed
    maxTurning = truckData.maxTurning
    keyStates = { left: false, right: false, backward: false, forward: false, space: false }

    model: Object3D | null = null
    leftLightModel: Object3D | undefined = undefined
    rightLightModel: Object3D | undefined = undefined
    brakeLightModel: Object3D | undefined = undefined
    positionedIn = ""
    originWarehouse: Object3D | undefined = undefined
    connection: Object3D | undefined = undefined
    destinationWarehouse: Object3D | undefined = undefined
    truckCenteredAtWarehouse: Object3D | undefined = undefined

    constructor() {
    }

    collides(scene: Scene, truckInNewPosition: Object3D): boolean {
        const leftLightModel = truckInNewPosition.getObjectByName("truckLeftLight")!
        const rightLightModel = truckInNewPosition.getObjectByName("truckRightLight")!
        const brakeLightModel = truckInNewPosition.getObjectByName("truckBrakeLight")!
        const truckFront = truckInNewPosition.getObjectByName("truckFront")!

        const realWorldTruckPosition = truckInNewPosition.getWorldPosition(new Vector3())
        const realWorldLeftLightPosition = leftLightModel.getWorldPosition(new Vector3())
        const realWorldRightLightPosition = rightLightModel.getWorldPosition(new Vector3())
        const realWorldFrontPosition = truckFront.getWorldPosition(new Vector3())

        const realWorldBrakeLightPosition = brakeLightModel.getWorldPosition(new Vector3())

        if(this.originWarehouse != null) {
            let truckCenteredAtWarehouseFront: Object3D | undefined
            let truckCenteredAtWarehouseBack: Object3D | undefined
            let connectionCenteredAtWarehouse: Object3D | undefined
            let truckInBasePosition: Vector3 | undefined
            let offset: number | undefined
            let collidesWithBorders: boolean | undefined

            switch (this.positionedIn) {
                // Warehouse
                case "w":
                    let isNearAConnection = false
                    // See if it's going in the direction of an available connection
                    const warehouseObj = this.originWarehouse
                    let nearestConnectionAngle: number | null = null
                    let nearestConnection: Object3D | null = null

                    const truckAngle = Utils.getOrientation(warehouseObj, truckInNewPosition)
                    Roads.connectorPairs.forEach(function (pair) {
                        if (warehouseObj.name == pair[0].name) {
                            const connectionAngle = pair[1].rotation.y

                            const angleDifference =  Math.abs(truckAngle - connectionAngle)
                            if(angleDifference < 0.20) {
                                if(nearestConnectionAngle == null || nearestConnectionAngle > angleDifference) {
                                    nearestConnectionAngle = angleDifference
                                    nearestConnection = pair[1]
                                }
                                isNearAConnection = true
                            }
                            console.log(Math.abs(truckAngle - connectionAngle))
                        }
                    })

                    const collisionElement = this.speed >= 0 ? realWorldFrontPosition : realWorldBrakeLightPosition

                    const collidesWithRoundaboutBorders =  Truck.roundaboutCollision(
                        collisionElement,
                        this.originWarehouse.position
                    )

                    if(collidesWithRoundaboutBorders) {
                        if(isNearAConnection && nearestConnection != null) {
                            this.positionedIn = "c"
                            this.connection = nearestConnection // Connection as 1

                            //this.direction = MathUtils.radToDeg(-(nearestConnection as Object3D).rotation.y - Math.PI/2) + 180
                            //this.wheelDirection = 0
                        } else {
                            return true
                        }
                    }

                    console.log(this.positionedIn + " - " + this.originWarehouse.name)

                    const collidesWithTree = Truck.roundaboutTreeCollision(
                        collisionElement,
                        this.originWarehouse.position
                    )

                    if(collidesWithTree)
                        return true

                    break
                // Connection
                case "c":
                    const truckRealWorldPosition = truckInNewPosition.getWorldPosition(new Vector3())
                    const zp = (truckRealWorldPosition.z - this.originWarehouse.position.z) * Math.cos(-this.connection!.rotation.y) + (truckRealWorldPosition.x - this.originWarehouse.position.x) * Math.sin(-this.connection!.rotation.y)

                    collidesWithBorders = zp*2 < -roadData.roadWidth / 2 || zp*2 > roadData.roadWidth / 2

                    if (this.speed > 0 && collidesWithBorders)
                        return true

                    const realWorldTruckPosition = truckInNewPosition.getWorldPosition(new Vector3())

                    if(this.destinationWarehouse != undefined && Utils.squared2DDistance(this.destinationWarehouse!.position, realWorldTruckPosition) < Math.pow(roadData.roundaboutRadius*0.8,2)) {
                        this.positionedIn = "w"
                        this.originWarehouse = this.destinationWarehouse
                        this.connection = undefined
                        this.destinationWarehouse = undefined
                    } else if(Utils.squared2DDistance(this.originWarehouse!.position, realWorldTruckPosition) >= Math.pow(roadData.roundaboutRadius * 1.65,2)) {
                        this.positionedIn = "r"

                        const split = this.connection!.name.split("-")

                        for (const value of split) {
                            if(value != this.originWarehouse!.name) {
                                this.destinationWarehouse = scene.getObjectByName(value)
                            }
                        }

                        this.slope = Utils.getSlope(this.originWarehouse!, this.destinationWarehouse!, roadData.connectorLength - 0.10)
                    } else if(Utils.squared2DDistance(this.originWarehouse!.position, realWorldTruckPosition) < Math.pow(roadData.roundaboutRadius*0.8,2)) {
                        this.positionedIn = "w"
                        this.connection = undefined
                        this.destinationWarehouse = undefined
                    }
                    console.log(this.positionedIn + " - " + this.connection?.name)
                    break
                // Road
                case "r":
                    const realWorldTruckPosition2 = truckInNewPosition.getWorldPosition(new Vector3())
                    const zp2 = (realWorldTruckPosition2.z - this.originWarehouse.position.z) * Math.cos(-this.connection!.rotation.y) + (realWorldTruckPosition2.x - this.originWarehouse.position.x) * Math.sin(-this.connection!.rotation.y)

                    collidesWithBorders = zp2*2 < -roadData.roadWidth / 2 || zp2*2 > roadData.roadWidth / 2

                    if (collidesWithBorders)
                        return true

                    if (Utils.squared2DDistance(this.originWarehouse!.position, realWorldTruckPosition2) < Math.pow(roadData.roundaboutRadius * 1.65,2)) {
                        this.slope = 0
                        truckInNewPosition.position.y = this.originWarehouse!.position.y + roadData.roundaboutHeight + 0.24
                        this.positionedIn = "c"
                        this.destinationWarehouse = undefined
                    } else if (Utils.squared2DDistance(this.destinationWarehouse!.position, realWorldTruckPosition2) < Math.pow(roadData.roundaboutRadius * 1.65,2)) {
                        this.slope = 0
                        this.positionedIn = "c"
                        truckInNewPosition.position.y = this.destinationWarehouse!.position.y + roadData.roundaboutHeight + 0.24
                        const temp = this.destinationWarehouse
                        this.destinationWarehouse = this.originWarehouse
                        this.originWarehouse = temp
                    }
                    console.log(this.positionedIn + " - " + this.destinationWarehouse?.name)
                    break
            }
        }
        return false
    }

    private static roundaboutTreeCollision(truck: Vector3, warehouse: Vector3): boolean {
        return Utils.squared2DDistance(truck, warehouse) < Math.pow(0.25,2)
    }

    private static roundaboutCollision(truck: Vector3, warehouse: Vector3): boolean {
        return Utils.squared2DDistance(truck, warehouse) > Math.pow(roadData.roundaboutRadius,2)
    }

    static rotateWheels(wheelsDirection: number) {
        if(ModelLoader.truckModel != null) {
            ModelLoader.truckModel.children[0].children[0].children[0].children[7].rotation.z = -wheelsDirection
            ModelLoader.truckModel.children[0].children[0].children[0].children[4].rotation.z = wheelsDirection
        }
    }
}
