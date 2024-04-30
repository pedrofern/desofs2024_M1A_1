export default interface ITruckDTO {
    truckId: string;
    tare: number;
    loadCapacity: number;
    maximumBattery: number;
    autonomy: number;
    chargingTime: number;
    active: boolean;
}