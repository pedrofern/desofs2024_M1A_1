package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.WarehouseJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;
import pt.isep.desofs.m1a.g1.repository.utils.SpecificationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class WarehouseJpaRepositoryImpl implements WarehouseRepository {

    @Autowired
    private WarehouseJpaRepo repo;

    private final WarehouseJpaMapper mapper = WarehouseJpaMapper.INSTANCE;

    @Override
    public List<Warehouse> findAll() {

        List<WarehouseJpa> warehouseJpaList = repo.findAll();
        if (!warehouseJpaList.isEmpty()) {
            return warehouseJpaList.stream()
                    .map(mapper::toDomainModel)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Page<Warehouse> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        SpecificationHelper.removePageFilters(filters);
        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortBy));
        Specification<WarehouseJpa> specification = SpecificationHelper.getSpecifications(filters);
        return repo.findAll(specification, pageable).map(mapper::toDomainModel);
    }

    @Override
    public Optional<Warehouse> findByIdentifier(Long identifier) {
        Optional<WarehouseJpa> w = repo.findByIdentifier(identifier);
        return w.map(mapper::toDomainModel);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        WarehouseJpa savedWarehouse = repo.save(mapper.toDatabaseEntity(warehouse));
        return mapper.toDomainModel(savedWarehouse);
    }

    @Override
    public Warehouse update(Warehouse warehouse) {
        WarehouseJpa existingWarehouse = repo.findByIdentifier(warehouse.getIdentifier()).orElseThrow();
        existingWarehouse.setDesignation(warehouse.getDesignation());
        existingWarehouse.setStreetName(warehouse.getAddress().getStreetName());
        existingWarehouse.setDoorNumber(warehouse.getAddress().getDoorNumber());
        existingWarehouse.setCity(warehouse.getAddress().getCity());
        existingWarehouse.setCountry(warehouse.getAddress().getCountry());
        existingWarehouse.setZipCode(warehouse.getAddress().getZipCode());
        existingWarehouse.setLatitude(warehouse.getGeographicCoordinates().getLatitude());
        existingWarehouse.setLongitude(warehouse.getGeographicCoordinates().getLongitude());
        existingWarehouse.setActive(warehouse.isActive());
        WarehouseJpa savedWarehouse = repo.save(existingWarehouse);
        return mapper.toDomainModel(savedWarehouse);
    }
}
