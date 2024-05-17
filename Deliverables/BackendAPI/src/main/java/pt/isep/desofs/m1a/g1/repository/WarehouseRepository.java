package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository {

    List<Warehouse> findAll();

    Optional<Warehouse> findByIdentifier(Long identifier);

    Warehouse save(Warehouse warehouse);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);
}
