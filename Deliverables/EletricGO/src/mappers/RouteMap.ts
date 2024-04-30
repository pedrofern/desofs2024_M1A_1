import { IRoute } from "src/model/routes/IRoute";
import { RouteDTO } from "src/dtos/routes/routeDto";
import { ICreateRouteDTO } from "src/dtos/routes/ICreateRouteDTO";
import { IUpdateRouteDTO } from "src/dtos/routes/IUpdateRouteDTO";

export class RouteMap {
    public static toModel(dto: RouteDTO) : IRoute {
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

    public static toModelList(dtos: RouteDTO[]) : IRoute[] {
        const list: IRoute[] = [];

        dtos.forEach(r => {
            list.push(this.toModel(r));
        });

        return list;
    }

    public static toDTO(model: IRoute) : RouteDTO {
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
    
    public static toCreationDTO(model: IRoute) : ICreateRouteDTO {
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
    
    public static toUpdateDTO(model: IRoute) : IUpdateRouteDTO {
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