export default interface ITruck {
    truckId: string;
    tare: number;
    loadCapacity: number;
    maximumBattery: number;
    autonomy: number;
    chargingTime: number;
    active: boolean;
}