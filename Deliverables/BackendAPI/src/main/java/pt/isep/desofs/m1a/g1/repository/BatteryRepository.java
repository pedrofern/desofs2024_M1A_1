package pt.isep.desofs.m1a.g1.repository;

import pt.isep.desofs.m1a.g1.model.truck.Battery;

import java.util.Optional;

public interface BatteryRepository {

    Battery save(Battery battery);

    Optional<Battery> findById(String id);
}