import * as THREE from "three";
import {IWarehouseDto} from "../../../dtos/warehouse/IWarehouseDto";
import {round} from "three/examples/jsm/nodes/shadernode/ShaderNodeBaseElements";
import {roadData} from "./default-data";
import {TextureLoader} from "./texture-loader";

export class Roundabout {

    static create() {
        const roundabout = new THREE.Object3D();

        const r = roadData.roundaboutRadius

        /* The roundabout */
        const geometry = new THREE.CylinderGeometry(r, r, roadData.roundaboutHeight, 32 );
        const material = new THREE.MeshStandardMaterial( {map: TextureLoader.roundaboutTexture} );
        const cylinder = new THREE.Mesh( geometry, material );
        roundabout.add( cylinder );

        /* The roundabout outer circle border */
        const geometry4 = new THREE.RingGeometry( r-0.06, r-0.01, 70 );
        const material4 = new THREE.MeshBasicMaterial( { color: roadData.roadBorderColor, side: THREE.DoubleSide } );
        const mesh2 = new THREE.Mesh( geometry4, material4 );
        mesh2.rotateX(Math.PI / 2)
        mesh2.position.setY(roadData.roundaboutHeight-0.08)
        roundabout.add(mesh2);

        /* The roundabout inner circle */
        const geometry2 = new THREE.CircleGeometry( roadData.roundaboutInnerRadius, 32 );
        const material2 = new THREE.MeshStandardMaterial( { map: TextureLoader.dirtTexture, side: THREE.BackSide} );
        const circle = new THREE.Mesh( geometry2, material2 );
        circle.rotation.x = Math.PI/2;
        circle.position.setY(roadData.roundaboutHeight/2 + 0.005);
        roundabout.add( circle );

        /* The roundabout inner circle border */
        const geometry3 = new THREE.RingGeometry( roadData.roundaboutInnerRadius-0.01, roadData.roundaboutInnerRadius + 0.05, 32 );
        const material3 = new THREE.MeshBasicMaterial( { color: roadData.roadBorderColor, side: THREE.DoubleSide } );
        const mesh = new THREE.Mesh( geometry3, material3 );
        mesh.rotateX(Math.PI / 2)
        mesh.position.setY(roadData.roundaboutHeight-0.08)
        roundabout.add( mesh );

        return roundabout
    }
}
