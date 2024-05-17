package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.truck.Battery;

@Repository
public interface BatteryRepository {

    Battery save(Battery battery);

    Battery findByBatteryId(long batteryId);
}