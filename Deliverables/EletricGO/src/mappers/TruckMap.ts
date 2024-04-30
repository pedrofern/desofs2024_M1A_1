import ITruckDTO from "src/dtos/truck/ITruckDTO";
import ITruck from "src/model/ITruck";

export class TruckMap {

    public static toModel(dto: ITruckDTO): ITruck {
        return{
            truckId: dto.truckId,
            tare: dto.tare,
            loadCapacity: dto.loadCapacity,
            maximumBattery: dto.maximumBattery,
            autonomy: dto.autonomy,
            chargingTime: dto.chargingTime,
            active: dto.active
        }
    }

    public static toModelList(dtos: ITruckDTO[]): ITruck[] {
        const list: ITruck[] = []

        dtos.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }

    public static toDTO(truck: ITruck): ITruckDTO {
        return{
            truckId: truck.truckId,
            tare: truck.tare,
            loadCapacity: truck.loadCapacity,
            maximumBattery: truck.maximumBattery,
            autonomy: truck.autonomy,
            chargingTime: truck.chargingTime,
            active: truck.active
        }
    }  
    
    public static toDTOList(trucks: ITruck[]): ITruckDTO[] {
        const list: ITruck[] = []

        trucks.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }
}