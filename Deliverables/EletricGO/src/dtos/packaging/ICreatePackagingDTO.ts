export interface ICreatePackagingDTO {
    packagingId: string;
    deliveryId: number;
    truckId: number;
    loadTime: string;
    unloadTime: string;
    x: number;
    y: number;
    z: number;
}
