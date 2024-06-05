package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.repository.DeliveryPlanRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryPlanService;

@Service
public class DeliveryPlanServiceImpl implements DeliveryPlanService {

    @Autowired
    private DeliveryPlanRepository routeRepository;

    @Override
    public DeliveryPlan getDeliveryPlan(Long deliveryPlanId) {
        return routeRepository.findByDeliveryPlanId(deliveryPlanId);
    }
}
