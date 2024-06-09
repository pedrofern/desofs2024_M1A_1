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
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.TruckJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.TruckJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;
import pt.isep.desofs.m1a.g1.repository.utils.SpecificationHelper;

import java.util.List;
import java.util.Map;
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
    public Page<Truck> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        SpecificationHelper.removePageFilters(filters);
        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortBy));
        Specification<TruckJpa> specification = SpecificationHelper.getSpecifications(filters);
        return repo.findAll(specification, pageable).map(mapper::toDomainModel);
    }

    @Override
    public List<Truck> findAllActive() {
        return repo.findAll().stream()
                .filter(TruckJpa::isActive)
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Truck update(Truck truck) {
        Optional<TruckJpa> truckJpaOptional = repo.findByTruckId(truck.getTruckId());
        if (truckJpaOptional.isEmpty()) {
            throw new NotFoundException("Truck not found with identifier: " + truck.getTruckId());
        }

        TruckJpa existingTruckJpa = truckJpaOptional.get();
        existingTruckJpa.setActive(truck.isActive());
        existingTruckJpa.setTare(truck.getTare());
        existingTruckJpa.setLoadCapacity(truck.getLoadCapacity());
        existingTruckJpa.getBattery().setAutonomy(truck.getBattery().getAutonomy());
        existingTruckJpa.getBattery().setChargingTime(truck.getBattery().getChargingTime());
        existingTruckJpa.getBattery().setMaximumBattery(truck.getBattery().getMaximumBattery());

        TruckJpa savedTruckJpa = repo.save(existingTruckJpa);
        return mapper.toDomainModel(savedTruckJpa);
    }
}