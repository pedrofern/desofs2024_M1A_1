package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.isep.desofs.m1a.g1.controller.LogisticsController;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;

@Component
@Profile("bootstrap")
public class LogisticsBootstrapper implements CommandLineRunner {

    @Autowired
    private PackagingRepository packagingRepo;

    @Autowired
    private LogisticsService logisticsService;

    @Override
    public void run(String... args) throws Exception {
        // admin
        if (packagingRepo.findByPackagingId(10001).isEmpty()) {
            List<Packaging> p = packagingRepo.findByDeliveryId(20001);
            Localization l = new Localization(0,0,0);
            for (Packaging packaging : p) {
                if (packaging.getLocalization().equals(l)){
                    throw new IllegalArgumentException("Localization and TruckId are not unique for the given deliveryId");
                }
            }
            Packaging p1 = new Packaging(10001, 20001, 30001, "01:00" , "01:00", 0,0,0);
            Packaging p2 = new Packaging(20001, 33333, 44444, "01:00" , "01:00", 1,0,0);

            packagingRepo.save(p1);
            packagingRepo.save(p2);
        }
    }
}
