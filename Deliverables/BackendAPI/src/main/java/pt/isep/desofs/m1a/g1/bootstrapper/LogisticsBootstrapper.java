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
            Packaging p = new Packaging(10001, 20001, 30001, "01:00" , "01:00", 0,0,0);

            packagingRepo.save(p);
        }
    }
}
