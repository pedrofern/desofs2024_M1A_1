package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.repository.DeliveryPlanRepository;

@Component
@Profile("bootstrap")
@Order(3)
public class DeliveryPlanBootstrapper implements CommandLineRunner {

    @Autowired
    private DeliveryPlanRepository deliveryPlanRepo;

    @Override
    public void run(String... args) throws Exception {
        if (deliveryPlanRepo.findAll().isEmpty()) {
            deliveryPlanRepo.save(new DeliveryPlan(1L));
            deliveryPlanRepo.save(new DeliveryPlan(2L));
            deliveryPlanRepo.save(new DeliveryPlan(3L));
        }
    }
}
