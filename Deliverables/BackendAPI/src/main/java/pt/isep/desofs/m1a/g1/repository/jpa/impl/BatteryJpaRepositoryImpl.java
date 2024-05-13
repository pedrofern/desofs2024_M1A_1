package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.repository.BatteryRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.BatteryJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.BatteryJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.BatteryJpa;

import java.util.Optional;

@Repository
@Profile("jpa")
public class BatteryJpaRepositoryImpl implements BatteryRepository {

    @Autowired
    private BatteryJpaRepo repo;

    private BatteryJpaMapper mapper = BatteryJpaMapper.INSTANCE;

    @Override
    public Optional<Battery> findById(String id) {
        Optional<BatteryJpa> opt = repo.findById(id);
        return opt.map(mapper::toDomainModel);
    }

    @Override
    public Battery save(Battery battery) {
        BatteryJpa savedBattery = repo.save(mapper.toDatabaseEntity(battery));
        return mapper.toDomainModel(savedBattery);
    }
}