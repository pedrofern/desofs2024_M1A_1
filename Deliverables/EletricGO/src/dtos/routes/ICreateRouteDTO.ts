export interface ICreateRouteDTO {
    routeId: string;
    idDepartureWarehouse: string;
    idArrivalWarehouse: string;
    distance: number;
    time: number;
    energy: number;
    extraTime: number;
}