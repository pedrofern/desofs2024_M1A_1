export interface IDeliveryPlan {
  deliveryPlanId: number;
  truckId: string;
  date: string;
  deliveriesId: number[];
  paths: string[];
  cost: number;
}
