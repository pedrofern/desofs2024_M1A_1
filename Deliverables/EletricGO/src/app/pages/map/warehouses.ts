import * as THREE from "three";
import {Utils} from "./utils";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {ModelLoader} from "./model-loader";
import {Roundabout} from "./roundabout";
import {roadData} from "./default-data";
import Tree from "./tree";
import LightPole from "./light-pole";
import {TextureLoader} from "./texture-loader";

export class Warehouses {

    static addTo(scene: THREE.Scene, warehouses: IWarehouseDto[]) {
        warehouses.forEach(warehouse => {
            const coords = Utils.convertCoord(warehouse.longitude, warehouse.latitude, 0);

            // The main object
            const object = new THREE.Object3D
            object.name = warehouse.designation;
            object.position.set(coords.x, coords.y, coords.z);

            // The roundabout
            const roundabout = Roundabout.create()
            roundabout.name = warehouse.designation + "Roundabout";
            object.add(roundabout)

            // The warehouse figure (has a small translation out of the roundabout)
            const cube = ModelLoader.warehouseModel.clone(true)
            cube.translateZ(roadData.roundaboutOffset)
            cube.name = warehouse.designation;
            object.add(cube);

            // The warehouse connector
            const geometry2 = new THREE.BoxGeometry(2, roadData.roundaboutHeight, roadData.roadWidth*3);
            const material = new THREE.MeshBasicMaterial( {map: TextureLoader.roundaboutTexture} );
            const connector = new THREE.Mesh( geometry2, material);
            connector.translateZ(roadData.roundaboutOffset - 2)
            connector.position.y += roadData.connectorLevelDifference
            connector.rotateY(Math.PI / 2)
            object.add(connector);

            // The light pole
            const lightPole = new LightPole() as THREE.Object3D
            lightPole.translateZ(roadData.roundaboutOffset-1.7)
            lightPole.translateX(0.65)
            //connector.position.y += roadData.connectorLevelDifference
            lightPole.rotateY(Math.PI / 2)
            object.add(lightPole);

            // The other light pole
            const lightPole2 = new LightPole() as THREE.Object3D
            lightPole2.translateZ(roadData.roundaboutOffset-1.7)
            lightPole2.translateX(-0.65)
            //connector.position.y += roadData.connectorLevelDifference
            lightPole2.rotateY(Math.PI / 2)
            //object.add(lightPole2);

            // The tree
            const tree = new Tree() as THREE.Object3D
            object.add(tree)

            // The text sprite
            const text = Utils.makeTextSprite(warehouse.designation, {})
            text.position.setY(5);
            object.add(text)

            scene.add(object)
            object.updateMatrixWorld()
        })
    }
}
