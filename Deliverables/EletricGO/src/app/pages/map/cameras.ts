import * as THREE from "three";
import {OrbitControls} from "three/examples/jsm/controls/OrbitControls";
import {cameraData} from "./default-data";
import {Object3D, Vector3} from "three";

export class Cameras {

    static defaultFocus: Vector3
    static previousPosition: Vector3

    static orbitCamera: THREE.PerspectiveCamera
    static orbitControl: OrbitControls

    static addTo(scene: THREE.Scene, renderer: THREE.WebGLRenderer, screenWidth: number, screenHeight: number) {
        this.defaultFocus = scene.position

        /* Perspective Camera */
        const aspect = screenWidth / screenHeight;

        this.orbitCamera = new THREE.PerspectiveCamera(
            cameraData.orbit.viewAngle,
            aspect,
            cameraData.orbit.near,
            cameraData.orbit.far
        );

        this.orbitCamera.position.set(cameraData.orbit.x, cameraData.orbit.y, cameraData.orbit.z);
        this.orbitCamera.lookAt(this.defaultFocus);
        scene.add(this.orbitCamera);

        /* Orbit Control */
        this.orbitControl = new OrbitControls(this.orbitCamera, renderer.domElement);
    }

    static focusIn(warehouse: Object3D) {
        this.previousPosition = this.orbitCamera.position.clone()
        this.orbitCamera.position.set(warehouse.position.x + 20, warehouse.position.y + 12, warehouse.position.z + 20);
        this.orbitCamera.lookAt(warehouse.position);
    }

    static focusDefault() {
        this.orbitCamera.position.set(cameraData.orbit.x, cameraData.orbit.y, cameraData.orbit.z)
        this.orbitCamera.lookAt(this.defaultFocus);
    }
}
