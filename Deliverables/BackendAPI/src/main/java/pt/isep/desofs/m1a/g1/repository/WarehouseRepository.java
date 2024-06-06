package pt.isep.desofs.m1a.g1.repository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface WarehouseRepository {

    List<Warehouse> findAll();

    Page<Warehouse> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);

    Optional<Warehouse> findByIdentifier(Long identifier);

    Warehouse save(Warehouse warehouse);

    Warehouse update(Warehouse warehouse);
}
