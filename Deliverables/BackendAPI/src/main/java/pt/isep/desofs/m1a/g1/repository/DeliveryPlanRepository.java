package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;

import java.util.List;

@Repository
public interface DeliveryPlanRepository {

    List<DeliveryPlan> findAll();

    DeliveryPlan findByDeliveryPlanId(Long id);

    DeliveryPlan save(DeliveryPlan delivery);
}
