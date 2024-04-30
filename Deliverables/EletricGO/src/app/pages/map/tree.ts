import * as THREE from "three";
import {Utils} from "./utils";

export default class Tree {
    constructor() {
        const tree = new THREE.Object3D();

        const treeCrownColor = 0x498c2c;
        const treeTrunkColor = 0x4b3f2f;

        const treeHeights = [0.7, 0.8, 0.9];
        const height = Utils.pickRandomly(treeHeights);

        const treeTrunkGeometry = new THREE.BoxGeometry(0.15, 0.15, height);
        const treeTrunkMaterial = new THREE.MeshLambertMaterial({
            color: treeTrunkColor
        });
        const treeCrownMaterial = new THREE.MeshLambertMaterial({
            color: treeCrownColor
        });

        const trunk = new THREE.Mesh(treeTrunkGeometry, treeTrunkMaterial);
        trunk.position.set(0, height / 2, 0)
        trunk.castShadow = true;
        trunk.receiveShadow = true;
        trunk.rotateX(Math.PI / 2)
        tree.add(trunk);

        const crown = new THREE.Mesh(
            new THREE.SphereGeometry(height / 2, 30, 30),
            treeCrownMaterial
        );
        crown.position.y = height;
        crown.castShadow = true;
        crown.receiveShadow = false;
        tree.add(crown);

        return tree;
    }
}
