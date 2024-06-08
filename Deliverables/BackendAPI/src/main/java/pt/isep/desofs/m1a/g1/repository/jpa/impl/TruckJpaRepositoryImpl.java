package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.TruckJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.TruckJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class TruckJpaRepositoryImpl implements TruckRepository {

    @Autowired
    private TruckJpaRepo repo;

    private TruckJpaMapper mapper = TruckJpaMapper.INSTANCE;

    @Override
    public Optional<Truck> findByTruckId(Long id) {
        Optional<TruckJpa> opt = repo.findByTruckId(id);
        if (opt.isPresent()) {
            return Optional.of(mapper.toDomainModel(opt.get()));
        }
        return Optional.empty();
    }

    @Override
    public Truck save(Truck truck) {
        TruckJpa savedTruck = repo.save(mapper.toDatabaseEntity(truck));
        return mapper.toDomainModel(savedTruck);
    }

    @Override
    public List<Truck> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Truck> findAllActive() {
        return repo.findAll().stream()
                .filter(TruckJpa::isActive)
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }
}