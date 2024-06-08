package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.truck.Truck;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruckRepository {

    Truck save(Truck truck);

    Optional<Truck> findByTruckId(Long truckId);

    List<Truck> findAll();

    List<Truck> findAllActive();
}