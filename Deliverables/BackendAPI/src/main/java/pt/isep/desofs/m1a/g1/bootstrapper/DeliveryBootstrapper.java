package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.repository.DeliveryPlanRepository;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryService;

@Component
@Profile("bootstrap")
@Order(4)
public class DeliveryBootstrapper implements CommandLineRunner {

    @Autowired
    private DeliveryRepository repo;

    @Autowired
    private DeliveryService service;

    @Override
    public void run(String... args) throws Exception {
        if (repo.findAll().size() <= 10) {
            CreateDeliveryDTO d1 = new CreateDeliveryDTO("2024-01-01", 1000.00, 1L);
            service.createDelivery(d1);

            CreateDeliveryDTO d2 = new CreateDeliveryDTO("2024-01-01", 2000.00, 1L);
            service.createDelivery(d2);

            CreateDeliveryDTO d3 = new CreateDeliveryDTO("2024-01-01", 2000.00, 2L);
            service.createDelivery(d3);

            CreateDeliveryDTO d4 = new CreateDeliveryDTO("2024-01-02", 3000.00, 2L);
            service.createDelivery(d4);

            CreateDeliveryDTO d5 = new CreateDeliveryDTO("2024-01-02", 4000.00, 2L);
            service.createDelivery(d5);

            CreateDeliveryDTO d6 = new CreateDeliveryDTO("2024-01-02", 1000.00, 2L);
            service.createDelivery(d6);

            CreateDeliveryDTO d7 = new CreateDeliveryDTO("2024-01-03", 2000.00, 1L);
            service.createDelivery(d7);

            CreateDeliveryDTO d8 = new CreateDeliveryDTO("2024-01-03", 2000.00, 1L);
            service.createDelivery(d8);

            CreateDeliveryDTO d9 = new CreateDeliveryDTO("2024-01-03", 3000.00, 1L);
            service.createDelivery(d9);

            CreateDeliveryDTO d10 = new CreateDeliveryDTO("2024-01-03", 4000.00, 1L);
            service.createDelivery(d10);
        }
    }
}
