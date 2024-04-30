import * as THREE from "three";
import * as DefaultData from "./default-data"
import {guideData} from "./default-data";

export class VisualGuide {

    static addTo(scene: THREE.Scene) {
        const axes = new THREE.AxesHelper(guideData.axes.size);
        axes.position.set(guideData.axes.x, guideData.axes.y, guideData.axes.z);
        scene.add(axes);

        const gridFloor = new THREE.GridHelper(guideData.gridFloor.size, guideData.gridFloor.divisions);
        gridFloor.position.set(guideData.gridFloor.x, guideData.gridFloor.y, guideData.gridFloor.z);
        scene.add(gridFloor);

        const gridBack = new THREE.GridHelper(guideData.gridBack.size, guideData.gridBack.divisions);
        gridBack.position.set(guideData.gridBack.x, guideData.gridBack.y, guideData.gridBack.z);
        gridBack.rotation.x = Math.PI / 2;
        scene.add(gridBack);

        const gridLeft = new THREE.GridHelper(guideData.gridLeft.size, guideData.gridLeft.divisions);
        gridLeft.position.set(guideData.gridLeft.x, guideData.gridLeft.y, guideData.gridLeft.z);
        gridLeft.rotation.z = Math.PI / 2;
        scene.add(gridLeft);
    }
}
