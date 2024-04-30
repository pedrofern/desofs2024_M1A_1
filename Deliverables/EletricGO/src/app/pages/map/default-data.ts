import * as THREE from "three";

export const skyData = {
    url: "../../assets/textures/SceneTextures/ceu.jpg",
    nightUrl: "../../assets/textures/SceneTextures/ceu2.jpg",
}

export const truckData = {
    automaticMovement: false,
    automaticMovementWarehouses: ["Gondomar", "Póvoa de Varzim", "Gondomar", "Póvoa de Varzim"],
    url: "../../assets/models/GLTF format/truck/scene.gltf",
    reverseSoundUrl: "../../assets/sounds/0007474.mp3",
    scale: 0.003,
    maxSpeed: 3,
    reverseMaxSpeed: 0.80,
    initialDirection: 0.0, // Expressed in degrees
    acceleration: 0.5,
    normalDeaccelerationFactor: 5,
    turningSpeed: 65.0,
    maxTurning: 18.0,
    keyCodes: { left: "ArrowLeft", right: "ArrowRight", backward: "ArrowDown", forward: "ArrowUp", space: "Space" },
    shouldMove: false,
}

export const roadData = {
    roundaboutHeight: 0.2,
    roundaboutRadius: 2.5,
    roundaboutInnerRadius: 0.6,
    roundaboutInnerTextureUrl: "../../assets/textures/SceneTextures/terra.jpg",
    roundaboutTextureUrl: "../../assets/textures/SceneTextures/roundabout.jpg",
    roundaboutOffset: 4.5,
    roadHeight: 0.25,
    roadWidth: 1.2,
    roadColor: 0x222222,
    roadTextureUrl: "../../assets/textures/SceneTextures/road.jpg",
    roadBorderColor: 0x666666,
    connectorLength: 0,
    connectorLevelDifference: -0.003
}

export const warehouseData = {
    url: "../../assets/models/GLTF format/warehouse.glb",
}

export const lightsData = {
    ambientLight: { color: 0x222222, intensity: 0.4 },
    directLight: { color: 0xffffff, intensity: 0.7, position: new THREE.Vector3(1.0, 1.0, 1.0) },
}

export const fogData = {
    enabled: false,
    color: 0xe0e0e0,
    near: 50,
    far: 200
}

export const cameraData = {
    orbit: {
        viewAngle: 45,
        near: 0.1,
        far: 5000,
        x: 0,
        y: 50,
        z: 150
    }
}

export const guideData = {
    axes: {
        size: 250,
        x: 0,
        y: 40,
        z: 0,
    },
    gridFloor: {
        size: 200,
        divisions: 10,
        x: 0,
        y: 0,
        z: 0,
    },
    gridBack: {
        size: 200,
        divisions: 10,
        x: 0,
        y: 100,
        z: -100,
    },
    gridLeft: {
        size: 200,
        divisions: 10,
        x: -100,
        y: 100,
        z: 0,
    }
}
