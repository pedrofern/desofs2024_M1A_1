import * as THREE from "three";
import {Utils} from "./utils";

export default class LightPole {
    constructor() {
        const lightpole = new THREE.Object3D();

        const lampColor = 0xffffff;
        const treeTrunkColor = 0x444444;

        const height = 0.6

        const treeTrunkGeometry = new THREE.BoxGeometry(0.05, 0.05, height);
        const treeTrunkMaterial = new THREE.MeshBasicMaterial({
            color: treeTrunkColor
        });
        const treeCrownMaterial = new THREE.MeshBasicMaterial({
            color: lampColor
        });

        const pole = new THREE.Mesh(treeTrunkGeometry, treeTrunkMaterial);
        pole.position.set(0, height / 2, 0)
        pole.castShadow = true;
        pole.receiveShadow = true;
        pole.rotateX(Math.PI / 2)
        lightpole.add(pole);

        const lamp = new THREE.Mesh(
            new THREE.SphereGeometry(0.1, 30, 30),
            treeCrownMaterial
        );
        lamp.position.y = height;
        lamp.castShadow = true;
        lamp.receiveShadow = false;
        lightpole.add(lamp);

        // The light
        const light = new THREE.PointLight( 0xffffff, .8, 20);
        light.shadow.mapSize.width = 512;
        light.shadow.mapSize.height = 512;
        light.position.setY(height + height / 2);
        lightpole.add(light);

        return lightpole;
    }
}
