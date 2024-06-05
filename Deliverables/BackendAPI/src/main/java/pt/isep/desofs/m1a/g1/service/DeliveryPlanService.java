package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;

public interface DeliveryPlanService {

    DeliveryPlan getDeliveryPlanByDeliveryIdAndDeliveryWarehouseId(Long deliveryId, Long warehouseId);
}
