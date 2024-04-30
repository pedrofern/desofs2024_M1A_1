import { IDeliveryDto } from "src/dtos/delivery/IDeliveryDto";
import { IDelivery } from "src/model/IDelivery";

export class DeliveryMap {

    public static toModel(dto: IDeliveryDto): IDelivery {
        return {
            deliveryIdentifier: dto.deliveryIdentifier,
            deliveryDate: dto.deliveryDate,
            weight: dto.weight,
            warehouseId: dto.warehouseId
        }
    }

    public static toDTO(model: IDelivery): IDeliveryDto {
        return {
            deliveryIdentifier: model.deliveryIdentifier,
            deliveryDate: model.deliveryDate,
            weight: model.weight,
            warehouseId: model.warehouseId
        }
    }

    public static toList(dto: IDeliveryDto[]): IDelivery[] {
        const list: IDelivery[] = []

        dto.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }
}