package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Route;

@Repository
public interface RouteRepository {

    Route save(Route delivery);

    int count();
}
