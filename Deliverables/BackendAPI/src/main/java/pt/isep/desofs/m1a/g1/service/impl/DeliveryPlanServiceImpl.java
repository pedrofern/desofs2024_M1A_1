package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.model.delivery.Route;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.RouteRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryPlanService;

import java.util.List;

@Service
public class DeliveryPlanServiceImpl implements DeliveryPlanService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryPlan getDeliveryPlanByDeliveryIdAndDeliveryWarehouseId(Long deliveryId, Long warehouseId) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryIdAndWarehouseId(deliveryId, warehouseId);
        List<Route> routes = routeRepository.findByArrivalWarehouseId(warehouseId);
        return new DeliveryPlan(routes, deliveries);
    }
}
