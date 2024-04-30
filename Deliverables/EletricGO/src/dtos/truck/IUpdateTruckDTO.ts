export default interface IUpdateTruckDTO {
    tare: number;
    loadCapacity: number;
    maximumBattery: number;
    autonomy: number;
    chargingTime: number;
    active: boolean;
}