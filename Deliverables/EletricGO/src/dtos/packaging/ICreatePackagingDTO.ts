export interface ICreatePackagingDTO{
  packagingId: string;
  deliveryId: string;
  truckId: string;
  x: number;
  y: number;
  z: number;
  loadTime: number;
  unloadTime: number;
}