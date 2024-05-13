package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.BatteryRepository;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;

@Component
@Profile("bootstrap")
public class TruckBootstrapper implements CommandLineRunner {

    @Autowired
    private TruckRepository truckRepo;

    @Autowired
    private BatteryRepository batteryRepo;

    @Override
    public void run(String... args) throws Exception {
        // truck
        if (truckRepo.findById("truckId1").isEmpty()) {
            Battery battery = new Battery(100.0, 200.0, 1.5);
            batteryRepo.save(battery);

            Truck truck = new Truck("truckId1", 2000.0, 10000.0, true, battery);
            truckRepo.save(truck);
        }
    }
}