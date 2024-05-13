package pt.isep.desofs.m1a.g1.repository;

import pt.isep.desofs.m1a.g1.model.truck.Truck;

import java.util.Optional;

public interface TruckRepository {

    Truck save(Truck truck);

    Optional<Truck> findById(String id);
}