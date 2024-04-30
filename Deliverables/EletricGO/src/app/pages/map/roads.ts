import * as THREE from "three";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {roadData} from "./default-data";
import {Utils} from "./utils";
import {TextureLoader} from "./texture-loader";

export class Roads {

    static connectorPairs: (THREE.Object3D)[][] = [];

    static addTo(scene: THREE.Scene, warehouses: IWarehouseDto[]) {
        roadData.connectorLength = roadData.roundaboutRadius * 1.1

        for (let i = 0; i < warehouses.length; i++) {
            const warehouse = scene.getObjectByName(warehouses[i].designation) as THREE.Object3D;
            const previous = i - 1;
            const next = i + 1;
            if(previous >= 0) {
                const previousWarehouse = scene.getObjectByName(warehouses[previous].designation) as THREE.Object3D;

                //Draw the connectors
                const connectorGeometry = new THREE.BoxGeometry(roadData.connectorLength, roadData.roundaboutHeight, roadData.roadWidth);
                const basicMaterial = new THREE.MeshBasicMaterial( { color: roadData.roadColor } );
                const connector1 = new THREE.Mesh( connectorGeometry, basicMaterial);
                let rotation = Utils.getOrientation(warehouse, previousWarehouse)
                connector1.name = warehouse.name + "-" + previousWarehouse.name
                connector1.position.add(warehouse.position)
                connector1.position.y += roadData.connectorLevelDifference
                connector1.geometry.translate(roadData.connectorLength / 2 + 0.01, 0, 0)
                connector1.rotateY(-rotation-Math.PI)
                scene.add(connector1);

                const connectorPlaneTopGeometry = new THREE.PlaneGeometry( roadData.connectorLength / 1.65, roadData.roadWidth );
                const topPlaneMaterial = new THREE.MeshStandardMaterial( {map: TextureLoader.roadConnectorsTexture, side: THREE.FrontSide} );
                const connector1TopPlane = new THREE.Mesh( connectorPlaneTopGeometry, topPlaneMaterial );
                connector1TopPlane.receiveShadow = true
                connector1TopPlane.material.map!.repeat.set(0.5,1)
                connector1TopPlane.material.map!.wrapS = THREE.RepeatWrapping
                connector1TopPlane.position.add(warehouse.position)
                connector1TopPlane.position.y += roadData.connectorLevelDifference+0.101
                connector1TopPlane.geometry.translate(roadData.connectorLength * 1.2, 0, 0)
                connector1TopPlane.rotateY(-rotation-Math.PI)
                connector1TopPlane.rotateX(-Math.PI/2)
                scene.add( connector1TopPlane );

                const connector2 = new THREE.Mesh( connectorGeometry, basicMaterial);
                rotation = Utils.getOrientation(previousWarehouse, warehouse)
                connector2.name = previousWarehouse.name + "-" + warehouse.name
                connector2.position.add(previousWarehouse.position)
                connector2.position.y += roadData.connectorLevelDifference
                connector2.geometry.translate(roadData.connectorLength / 2 + 0.01, 0, 0)
                connector2.rotateY(-rotation-Math.PI)
                scene.add(connector2);

                const connector2TopPlane = new THREE.Mesh( connectorPlaneTopGeometry, topPlaneMaterial );
                connector2TopPlane.receiveShadow = true
                connector2TopPlane.material.map!.repeat.set(0.5,1)
                connector2TopPlane.material.map!.wrapS = THREE.RepeatWrapping
                connector2TopPlane.position.add(previousWarehouse.position)
                connector2TopPlane.position.y += roadData.connectorLevelDifference+0.101
                connector2TopPlane.rotateY(-rotation-Math.PI)
                connector2TopPlane.rotateX(-Math.PI/2)
                scene.add( connector2TopPlane );

                Roads.connectorPairs.push([warehouse, connector1]);
                Roads.connectorPairs.push([previousWarehouse, connector2]);

                // Draw the connecting road
                const roadLength = Utils.getRoadLength(warehouse, previousWarehouse, roadData.connectorLength) + 0.07
                const geometry3 = new THREE.BoxGeometry(roadLength, roadData.roundaboutHeight, roadData.roadWidth);
                const road = new THREE.Mesh( geometry3, basicMaterial);
                road.position.setX(previousWarehouse.position.x - (previousWarehouse.position.x - warehouse.position.x) / 2)
                road.position.setY(roadData.connectorLevelDifference + previousWarehouse.position.y - (previousWarehouse.position.y - warehouse.position.y) / 2)
                road.position.setZ(previousWarehouse.position.z - (previousWarehouse.position.z - warehouse.position.z) / 2)

                rotation = Utils.getOrientation(previousWarehouse, warehouse)
                road.rotateY(-rotation)
                const slope = Utils.getSlope(previousWarehouse, warehouse, roadData.connectorLength)
                road.rotateZ(slope)
                scene.add(road);

                const roadTopPlaneMaterial = new THREE.MeshStandardMaterial( {map: TextureLoader.roadConnectorsTexture.clone(), side: THREE.FrontSide} );
                const roadPlaneTopGeometry = new THREE.PlaneGeometry( roadLength, roadData.roadWidth );
                const roadTopPlane = new THREE.Mesh( roadPlaneTopGeometry, roadTopPlaneMaterial );
                roadTopPlane.receiveShadow = true
                roadTopPlane.material.map!.repeat.set(roadLength/2,1)
                roadTopPlane.material.map!.wrapS = THREE.RepeatWrapping
                roadTopPlane.geometry.translate(0, 0, 0.1)
                roadTopPlane.position.set(road.position.x, road.position.y, road.position.z)
                roadTopPlane.rotateY(-rotation)
                roadTopPlane.rotateZ(slope)
                roadTopPlane.rotateX(-Math.PI/2)
                scene.add( roadTopPlane );
            }
        }

        /* Validates collisions between connectors and warehouse */
        warehouses.forEach(function(warehouse) {
            let collides
            do {
                collides = false
                let warehouseObject: THREE.Object3D | null = null
                Roads.connectorPairs.forEach(function (pair) {
                    if (warehouse.designation == pair[0].name) {
                        warehouseObject = pair[0]

                        if (Utils.rotationIsSame(pair[0], pair[1], Math.PI / 6))
                            collides = true
                    }
                });
                if(collides) {
                    warehouseObject!.rotateY(Math.PI / 6)
                }
            } while (collides)
        })
    }
}
