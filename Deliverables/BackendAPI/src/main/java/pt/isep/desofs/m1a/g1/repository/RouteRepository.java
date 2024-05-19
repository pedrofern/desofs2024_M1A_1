package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Route;

@Repository
public interface RouteRepository {

    Route findByRouteId(Long routeId);

    Route save(Route delivery);
}
