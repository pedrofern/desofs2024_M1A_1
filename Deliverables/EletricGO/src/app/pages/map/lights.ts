import * as THREE from "three";
import {lightsData} from "./default-data";
import {AmbientLight, DirectionalLight} from "three";

export class Lights {

    static directLight: DirectionalLight
    static ambientLight: AmbientLight

    static addTo(scene: THREE.Scene) {
        /* Direct Light */
        this.directLight = new THREE.DirectionalLight(lightsData.directLight.color, lightsData.directLight.intensity);
        this.directLight.position.setX(lightsData.directLight.position.x);
        this.directLight.position.setY(lightsData.directLight.position.y);
        this.directLight.position.setZ(lightsData.directLight.position.z);
        scene.add(this.directLight);

        /* Ambient Light */
        this.ambientLight = new THREE.AmbientLight(lightsData.ambientLight.color, lightsData.ambientLight.intensity);
        scene.add(this.ambientLight);
    }
}
