import IBattery from "./IBattery";

export default interface ITruck {
    truckId: number;
    tare: number;
    loadCapacity: number;
    active: boolean;
    battery: IBattery;
}
