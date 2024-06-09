import ITruckDTO from "src/dtos/truck/ITruckDTO";
import ITruck from "src/model/ITruck";
import IBatteryDTO from "../dtos/truck/IBatteryDTO";
import IBattery from "src/model/IBattery";

export class TruckMap {

    public static toModel(dto: ITruckDTO): ITruck {
        return{
            truckId: dto.truckId,
            tare: dto.tare,
            loadCapacity: dto.loadCapacity,
            active: dto.active,
            battery: this.toBatteryModel(dto.battery)
        }
    }

    public static toModelList(dtos: ITruckDTO[]): ITruck[] {
        return dtos.map(dto => this.toModel(dto));
    }

    public static toDTO(truck: ITruck): ITruckDTO {
        return {
            truckId: truck.truckId,
            tare: truck.tare,
            loadCapacity: truck.loadCapacity,
            active: truck.active,
            battery: this.toBatteryDTO(truck.battery)
        };
    }

    public static toDTOList(trucks: ITruck[]): ITruckDTO[] {
        return trucks.map(truck => this.toDTO(truck));
    }

    private static toBatteryModel(dto: IBatteryDTO): IBattery {
        return {
            batteryId: dto.batteryId,
            maximumBattery: dto.maximumBattery,
            autonomy: dto.autonomy,
            chargingTime: dto.chargingTime
        };
    }

    private static toBatteryDTO(model: IBattery): IBatteryDTO {
        return {
            batteryId: model.batteryId,
            maximumBattery: model.maximumBattery,
            autonomy: model.autonomy,
            chargingTime: model.chargingTime
        };
    }
}
