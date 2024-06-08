import IBatteryDTO from "./IBatteryDTO";

export default interface ITruckDTO {
    truckId: number;
    tare: number;
    loadCapacity: number;
    active: boolean;
    battery: IBatteryDTO;
}
