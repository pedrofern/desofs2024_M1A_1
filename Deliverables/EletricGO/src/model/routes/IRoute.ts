export interface IRoute {
    routeId: string;
    idDepartureWarehouse: string;
    idArrivalWarehouse: string;
    distance: number;
    time: number;
    energy: number;
    extraTime: number;
}