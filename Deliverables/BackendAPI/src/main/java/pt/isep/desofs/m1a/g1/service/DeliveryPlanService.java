package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;

public interface DeliveryPlanService {

    DeliveryPlan getDeliveryPlan(String deliveryDate, Long warehouseId);
}
