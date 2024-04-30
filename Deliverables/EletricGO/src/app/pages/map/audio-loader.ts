import {GLTFLoader} from 'three/examples/jsm/loaders/GLTFLoader';
import * as THREE from "three";
import * as DefaultData from "./default-data"
import {OBJLoader} from "three/examples/jsm/loaders/OBJLoader";
import {roadData, truckData} from "./default-data";
import {MeshBasicMaterial} from "three";

export class AudioLoader {

    static reserveSound: THREE.Audio;

    static loadTo(camera: THREE.Camera) {
        const listener = new THREE.AudioListener();
        camera.add(listener);

        this.reserveSound = new THREE.Audio(listener);

        const audioLoader = new THREE.AudioLoader();

        audioLoader.load(truckData.reverseSoundUrl, function( buffer ) {
            AudioLoader.reserveSound.setBuffer( buffer );
            AudioLoader.reserveSound.setLoop(true);
            AudioLoader.reserveSound.setVolume(0.5);
        });
    }
}
