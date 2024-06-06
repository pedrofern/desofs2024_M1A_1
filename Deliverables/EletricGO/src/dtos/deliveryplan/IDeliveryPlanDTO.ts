import { IRouteDTO } from "../../model/routes/IRouteDTO";
import { IDeliveryDto } from "../delivery/IDeliveryDto";

export interface IDeliveryPlanDTO {
    routes: IRouteDTO[];
    deliveries: IDeliveryDto[];
}
