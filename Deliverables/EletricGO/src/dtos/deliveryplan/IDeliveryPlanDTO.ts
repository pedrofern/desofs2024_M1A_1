export interface IDeliveryPlanDTO {
  deliveryPlanId: number;
  truckId: string;
  date: string;
  deliveriesId: number[];
  paths: string[];
  cost: number;
}
