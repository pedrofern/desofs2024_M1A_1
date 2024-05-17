package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.PackagingJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.PackagingJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.WarehouseJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class WarehouseJpaRepositoryImpl implements WarehouseRepository {

    @Autowired
    private WarehouseJpaRepo repo;

    private WarehouseJpaMapper mapper = WarehouseJpaMapper.INSTANCE;

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
    public Optional<Warehouse> findByIdentifier(Long identifier) {
        Optional<WarehouseJpa> w = repo.findByIdentifier(identifier);
        return w.map(warehouseJpa -> mapper.toDomainModel(warehouseJpa));
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        WarehouseJpa savedWarehouse = repo.save(mapper.toDatabaseEntity(warehouse));
        return mapper.toDomainModel(savedWarehouse);
    }

    @Override
    public boolean existsByIdentifier(Long identifier) {
        return repo.existsByIdentifier(identifier);
    }

    @Override
    public void deleteByIdentifier(Long identifier) {
        repo.deleteByIdentifier(identifier);
    }

}
