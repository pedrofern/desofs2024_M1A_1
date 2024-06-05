package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.RouteDTO;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.model.delivery.Route;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.RouteRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryPlanService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryPlanServiceImpl implements DeliveryPlanService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryPlan getDeliveryPlan(String deliveryDate, Long warehouseId) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryDateAndWarehouseId(deliveryDate, warehouseId);
        List<Route> routes = routeRepository.findByArrivalWarehouseId(warehouseId);

        List<DeliveryDTO> deliveriesDTO = deliveries.stream()
                .map(Delivery::convertToDTO)
                .collect(Collectors.toList());

        List<RouteDTO> routesDTO = routes.stream()
                .map(Route::convertToDTO)
                .collect(Collectors.toList());

        return new DeliveryPlan(routesDTO, deliveriesDTO);
    }
}
