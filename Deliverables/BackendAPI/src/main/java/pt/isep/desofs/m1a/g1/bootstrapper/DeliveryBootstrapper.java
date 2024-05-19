package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryService;

import java.time.LocalDate;

@Component
@Profile("bootstrap")
public class DeliveryBootstrapper implements CommandLineRunner {

    @Autowired
    private DeliveryRepository repo;

    @Autowired
    private DeliveryService service;

    @Override
    public void run(String... args) throws Exception {
        if (repo.findAll().size() >= 5) {
            CreateDeliveryDTO d1 = new CreateDeliveryDTO(LocalDate.now(), 1000.00, 1L);
            service.createDelivery(d1);

            CreateDeliveryDTO d2 = new CreateDeliveryDTO(LocalDate.now().plusDays(1), 2000.00, 1L);
            service.createDelivery(d2);

            CreateDeliveryDTO d3 = new CreateDeliveryDTO(LocalDate.now().plusDays(2), 2000.00, 1L);
            service.createDelivery(d3);

            CreateDeliveryDTO d4 = new CreateDeliveryDTO(LocalDate.now().plusDays(3), 3000.00, 1L);
            service.createDelivery(d4);

            CreateDeliveryDTO d5 = new CreateDeliveryDTO(LocalDate.now().plusDays(4), 4000.00, 1L);
            service.createDelivery(d5);
        }
    }
}
