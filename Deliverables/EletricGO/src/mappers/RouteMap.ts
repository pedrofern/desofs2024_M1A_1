import { IRouteDTO } from "src/model/routes/IRouteDTO";
import { RouteDTO } from "src/dtos/routes/routeDto";
import { ICreateRouteDTO } from "src/dtos/routes/ICreateRouteDTO";
import { IUpdateRouteDTO } from "src/dtos/routes/IUpdateRouteDTO";

export class RouteMap {
    public static toModel(dto: RouteDTO) : IRouteDTO {
        return {
            routeId: dto.routeId,
            idDepartureWarehouse: dto.idDepartureWarehouse,
            idArrivalWarehouse: dto.idArrivalWarehouse,
            distance: dto.distance,
            time: dto.time,
            energy: dto.energy,
            extraTime: dto.extraTime
        };
    }

    public static toModelList(dtos: RouteDTO[]) : IRouteDTO[] {
        const list: IRouteDTO[] = [];

        dtos.forEach(r => {
            list.push(this.toModel(r));
        });

        return list;
    }

    public static toDTO(model: IRouteDTO) : RouteDTO {
        return {
            routeId: model.routeId,
            idDepartureWarehouse: model.idDepartureWarehouse,
            idArrivalWarehouse: model.idArrivalWarehouse,
            distance: model.distance,
            time: model.time,
            energy: model.energy,
            extraTime: model.extraTime
        } as RouteDTO;
    }

    public static toCreationDTO(model: IRouteDTO) : ICreateRouteDTO {
        return {
            routeId: model.routeId,
            idDepartureWarehouse: model.idDepartureWarehouse,
            idArrivalWarehouse: model.idArrivalWarehouse,
            distance: model.distance,
            time: model.time,
            energy: model.energy,
            extraTime: model.extraTime
        } as ICreateRouteDTO;
    }

    public static toUpdateDTO(model: IRouteDTO) : IUpdateRouteDTO {
        return {
            idDepartureWarehouse: model.idDepartureWarehouse,
            idArrivalWarehouse: model.idArrivalWarehouse,
            distance: model.distance,
            time: model.time,
            energy: model.energy,
            extraTime: model.extraTime
        } as IUpdateRouteDTO;
    }
}
