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
        if (repo.findByDeliveryId(1L) == null) {
            CreateDeliveryDTO d = new CreateDeliveryDTO(LocalDate.now(), 1000.00, 1L);
            service.createDelivery(d);
        }

        if (repo.findByDeliveryId(2L) == null) {
            CreateDeliveryDTO d = new CreateDeliveryDTO(LocalDate.now().plusDays(1), 2000.00, 1L);
            service.createDelivery(d);
        }

        if (repo.findByDeliveryId(3L) == null) {
            CreateDeliveryDTO d = new CreateDeliveryDTO(LocalDate.now().plusDays(2), 2000.00, 1L);
            service.createDelivery(d);
        }

        if (repo.findByDeliveryId(4L) == null) {
            CreateDeliveryDTO d = new CreateDeliveryDTO(LocalDate.now().plusDays(3), 3000.00, 1L);
            service.createDelivery(d);
        }

        if (repo.findByDeliveryId(5L) == null) {
            CreateDeliveryDTO d = new CreateDeliveryDTO(LocalDate.now().plusDays(4), 4000.00, 1L);
            service.createDelivery(d);
        }
    }
}
