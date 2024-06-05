package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.controller.LogisticsController;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;

@Component
@Profile("bootstrap")
@Order(6)
public class LogisticsBootstrapper implements CommandLineRunner {

    @Autowired
    private PackagingRepository packagingRepo;

    @Autowired
    private LogisticsService logisticsService;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public void run(String... args) throws Exception {
        // admin
        if (packagingRepo.findByPackagingId("10001").isEmpty()) {
            SubmitLogisticsForm form = new SubmitLogisticsForm("10001",23L, 123L, "01:00", "01:00", 0, 0, 0);

            logisticsService.submitForm(form);
        }
        if (packagingRepo.findByPackagingId("20002").isEmpty()) {
            SubmitLogisticsForm form = new SubmitLogisticsForm("20002",23L, 123L, "01:00", "01:00", 1, 0, 0);

            logisticsService.submitForm(form);
        }
    }
}
