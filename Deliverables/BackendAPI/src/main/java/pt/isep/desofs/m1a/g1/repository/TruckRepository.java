package pt.isep.desofs.m1a.g1.repository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.truck.Truck;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TruckRepository {

    Truck save(Truck truck);

    Optional<Truck> findByTruckId(Long truckId);

    List<Truck> findAll();

    Page<Truck> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);

    List<Truck> findAllActive();
}