import { IDeliveryPlanDTO } from "src/dtos/deliveryplan/IDeliveryPlanDTO";
import { IDeliveryPlan } from "src/model/IDeliveryPlan";

export class DeliveryPlanMap {

    public static toModel(dto: IDeliveryPlanDTO): IDeliveryPlan {
        return {
            deliveryPlanId: dto.deliveryPlanId,
            truckId: dto.truckId,
            date: dto.date,
            deliveriesId: dto.deliveriesId,
            paths: dto.paths,
            cost: dto.cost
        }
    }

    public static toDTO(model: IDeliveryPlan): IDeliveryPlanDTO {
        return {
            deliveryPlanId: model.deliveryPlanId,
            truckId: model.truckId,
            date: model.date,
            deliveriesId: model.deliveriesId,
            paths: model.paths,
            cost: model.cost
        }
    }

    public static toList(dto: IDeliveryPlanDTO[]): IDeliveryPlan[] {
        let list = Array()

        dto.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }
}