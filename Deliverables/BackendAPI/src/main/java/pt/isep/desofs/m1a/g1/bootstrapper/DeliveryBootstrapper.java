package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryService;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

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
        if (repo.findByIdentifier(1L) == null) {
            Delivery d = new Delivery(1L, LocalDate.now(), 1000.00, "1");
            repo.save(d);
        }

        if (repo.findByIdentifier(2L) == null) {
            Delivery d = new Delivery(2L, LocalDate.now().plusDays(1), 2000.00, "1");
            repo.save(d);
        }

        if (repo.findByIdentifier(3L) == null) {
            Delivery d = new Delivery(3L, LocalDate.now().plusDays(2), 2000.00, "2");
            repo.save(d);
        }

        if (repo.findByIdentifier(4L) == null) {
            Delivery d = new Delivery(4L, LocalDate.now().plusDays(3), 3000.00, "2");
            repo.save(d);
        }

        if (repo.findByIdentifier(5L) == null) {
            Delivery d = new Delivery(5L, LocalDate.now().plusDays(4), 4000.00, "3");
            repo.save(d);
        }
    }
}
