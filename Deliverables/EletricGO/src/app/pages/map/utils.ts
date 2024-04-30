import * as THREE from "three";
import {Warehouses} from "./warehouses";
import {Object3D, Vector3} from "three";

export class Utils {

    // Pick a random value from an array
    static pickRandomly(array: number[]) {
        return array[Math.floor(Math.random() * array.length)];
    }

    // Convert the provided coordinates to the globe coordinates
    static convertCoord(long: number, lat: number, alt: number) {
        const out = new THREE.Vector3();
        // distribute to sphere
        out.set(
            ((50 + 50) / (8.7613 - 8.2451)) * (long - 8.2451) - 50,
            (50 / 800) * alt,
            ((50 + 50) / (42.1115 - 40.8387)) * (lat - 40.8387) - 50
        );

        return out;
    }

    // Validates collisions (by rotation)
    static rotationIsSame(a: THREE.Object3D, b: THREE.Object3D, margin: number) {
        const rotationDifference = Math.abs(((a.rotation.y - Math.PI / 2) - b.rotation.y)  % Math.PI)

        return (rotationDifference <= margin || rotationDifference >= Math.PI - margin)
    }

    static squared2DDistance(object1: Vector3, object2: Vector3) {
        return Math.pow((object1.x - object2.x),2) + Math.pow((object1.z - object2.z),2)
    }

    static makeTextSprite( message: string, parameters: any)
    {
        const fontface = Object.prototype.hasOwnProperty.call(parameters, "fontface") ? parameters["fontface"] : "Courier New";
        const fontsize = Object.prototype.hasOwnProperty.call(parameters, "fontsize") ? parameters["fontsize"] : 26;
        const borderThickness = Object.prototype.hasOwnProperty.call(parameters, "borderThickness") ? parameters["borderThickness"] : 4;
        const borderColor = Object.prototype.hasOwnProperty.call(parameters, "borderColor") ? parameters["borderColor"] : { r:0, g:0, b:0, a:1.0 };
        const backgroundColor = Object.prototype.hasOwnProperty.call(parameters, "backgroundColor") ? parameters["backgroundColor"] : { r:0, g:0, b:255, a:1.0 };
        const textColor = Object.prototype.hasOwnProperty.call(parameters, "textColor") ? parameters["textColor"] : { r:0, g:0, b:0, a:1.0 };
    
        const canvas = document.createElement('canvas');
        const context = canvas.getContext('2d');
        context!.font = "Bold " + fontsize + "px " + fontface;

        context!.fillStyle   = "rgba(" + backgroundColor.r + "," + backgroundColor.g + "," + backgroundColor.b + "," + backgroundColor.a + ")";
        context!.strokeStyle = "rgba(" + borderColor.r + "," + borderColor.g + "," + borderColor.b + "," + borderColor.a + ")";
        context!.fillStyle = "rgba("+textColor.r+", "+textColor.g+", "+textColor.b+", 1.0)";
        context!.fillText( message.padStart(12, ' '), borderThickness, fontsize + borderThickness);

        const texture = new THREE.Texture(canvas)
        texture.needsUpdate = true;
        const spriteMaterial = new THREE.SpriteMaterial( { map: texture } );
        const sprite = new THREE.Sprite( spriteMaterial );
        sprite.scale.set(0.5 * fontsize, 0.25 * fontsize, 0.75 * fontsize);

        canvas.remove()
        return sprite;
    }


    static getOrientation(warehouseJ: THREE.Object3D, warehouseI: THREE.Object3D) {
        return Math.atan2(warehouseJ.position.z - warehouseI.position.z, warehouseJ.position.x - warehouseI.position.x);
    }

    static getRoadLength(warehouseJ: THREE.Object3D, warehouseI: THREE.Object3D, connectorLength: number) {
        return Math.sqrt(
            Math.pow(Utils.getProjectionLength(warehouseJ, warehouseI, connectorLength),2) + Math.pow(Utils.getUnevenness(warehouseJ, warehouseI),2)
        )
    }

    static getUnevenness(warehouseJ: THREE.Object3D, warehouseI: THREE.Object3D) {
        return warehouseJ.position.y - warehouseI.position.y
    }

    static getProjectionLength(warehouseJ: THREE.Object3D, warehouseI: THREE.Object3D, connectorLength: number) {
        return Math.sqrt(Math.pow(warehouseJ.position.x - warehouseI.position.x, 2) + Math.pow(warehouseJ.position.z - warehouseI.position.z, 2)) - connectorLength * 2 - (connectorLength / 2) * 2
    }

    static getSlope(warehouseJ: THREE.Object3D, warehouseI: THREE.Object3D, connectorLength: number) {
        return Math.atan(this.getUnevenness(warehouseJ, warehouseI) / this.getProjectionLength(warehouseJ, warehouseI, connectorLength))
    }
}
