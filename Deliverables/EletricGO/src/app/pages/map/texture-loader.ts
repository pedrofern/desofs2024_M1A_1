import * as THREE from "three";
import * as DefaultData from "./default-data"
import {Texture} from "three";

export class TextureLoader {

    static dirtTexture: Texture
    static roadConnectorsTexture: Texture
    static roundaboutTexture: Texture
    static daySky: Texture
    static nightSky: Texture

    static loadTo(scene: THREE.Scene, renderer: THREE.WebGLRenderer) {
        // Draw the sky
        this.daySky = new THREE.TextureLoader().load(
            DefaultData.skyData.url,
            () => {
                const rt = new THREE.WebGLCubeRenderTarget(this.daySky.image.height);
                rt.fromEquirectangularTexture(renderer, this.daySky);
                scene.background = rt.texture;
            }
        );

        TextureLoader.nightSky = new THREE.TextureLoader().load(
            DefaultData.skyData.nightUrl
        );

        TextureLoader.dirtTexture = new THREE.TextureLoader().load(
            DefaultData.roadData.roundaboutInnerTextureUrl
        );

        TextureLoader.dirtTexture.wrapS = THREE.RepeatWrapping;
        TextureLoader.dirtTexture.wrapT = THREE.RepeatWrapping;
        TextureLoader.dirtTexture.repeat.set( 2, 2 );

        TextureLoader.roadConnectorsTexture = new THREE.TextureLoader().load(
            DefaultData.roadData.roadTextureUrl
        );

        TextureLoader.roadConnectorsTexture.wrapS = THREE.ClampToEdgeWrapping;
        TextureLoader.roadConnectorsTexture.wrapT = THREE.RepeatWrapping;

        TextureLoader.roundaboutTexture = new THREE.TextureLoader().load(
            DefaultData.roadData.roundaboutTextureUrl
        );

        TextureLoader.roundaboutTexture.wrapS = THREE.RepeatWrapping;
        TextureLoader.roundaboutTexture.wrapT = THREE.RepeatWrapping;
        TextureLoader.roundaboutTexture.repeat.set( 5, 5 );
    }
}
