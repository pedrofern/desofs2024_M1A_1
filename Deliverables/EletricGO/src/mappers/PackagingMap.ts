import { IPackagingDTO } from "src/dtos/packaging/IPackagingDTO";
import { IPackaging } from "src/model/IPackaging";

export class PackagingMap {

    public static toModel(dto: IPackagingDTO): IPackaging {
        return {
            packagingId: dto.packagingId,
            deliveryId: dto.deliveryId,
            truckId: dto.truckId,
            x: dto.x,
            y: dto.y,
            z: dto.z,
            loadTime: dto.loadTime,
            unloadTime: dto.unloadTime
        }
    }

    public static toDTO(model: IPackaging): IPackagingDTO {
        return {
            packagingId: model.packagingId,
            deliveryId: model.deliveryId,
            truckId: model.truckId,
            x: model.x,
            y: model.y,
            z: model.z,
            loadTime: model.loadTime,
            unloadTime: model.unloadTime
        }
    }

    public static toList(dto: IPackagingDTO[]): IPackaging[] {
        let list = Array()

        dto.forEach(element => {
            list.push(this.toModel(element))
        });

        return list
    }
}