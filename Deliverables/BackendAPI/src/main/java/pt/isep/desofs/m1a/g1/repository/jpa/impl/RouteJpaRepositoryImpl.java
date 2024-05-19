package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Route;
import pt.isep.desofs.m1a.g1.repository.RouteRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.RouteJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.RouteJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.RouteJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;

import java.util.Optional;

@Repository
@Profile("jpa")
public class RouteJpaRepositoryImpl implements RouteRepository {

    @Autowired
    private RouteJpaRepo repo;

    @Autowired
    private WarehouseJpaRepo wRepo;

    private final RouteJpaMapper mapper = RouteJpaMapper.INSTANCE;

    @Override
    public int count() {
        return repo.findAll().size();
    }

    @Override
    public Route save(Route route) {
        Optional<WarehouseJpa> arrivalWarehouse = wRepo.findByIdentifier(route.getIdArrivalWarehouse());
        if (arrivalWarehouse.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + route.getIdArrivalWarehouse());
        }
        Optional<WarehouseJpa> departureWarehouse = wRepo.findByIdentifier(route.getIdDepartureWarehouse());
        if (departureWarehouse.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + route.getIdDepartureWarehouse());
        }
        RouteJpa routeJpa = mapper.routeToRouteJpa(route);
        routeJpa.setArrivalWarehouse(arrivalWarehouse.get());
        routeJpa.setDepartureWarehouse(departureWarehouse.get());
        RouteJpa savedRouteJpa = repo.save(routeJpa);
        return mapper.routeJpaToRoute(savedRouteJpa);
    }
}
