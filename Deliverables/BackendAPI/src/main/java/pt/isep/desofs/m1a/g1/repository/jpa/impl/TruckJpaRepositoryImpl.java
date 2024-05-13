package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.TruckJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.TruckJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

import java.util.Optional;

@Repository
@Profile("jpa")
public class TruckJpaRepositoryImpl implements TruckRepository {

    @Autowired
    private TruckJpaRepo repo;

    private TruckJpaMapper mapper = TruckJpaMapper.INSTANCE;

    @Override
    public Optional<Truck> findById(String id) {
        Optional<TruckJpa> opt = repo.findById(id);
        return opt.map(mapper::toDomainModel);
    }

    @Override
    public Truck save(Truck truck) {
        TruckJpa savedTruck = repo.save(mapper.toDatabaseEntity(truck));
        return mapper.toDomainModel(savedTruck);
    }
}