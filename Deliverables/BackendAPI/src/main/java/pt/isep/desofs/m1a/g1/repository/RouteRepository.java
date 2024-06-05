package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Route;

import java.util.List;

@Repository
public interface RouteRepository {

    Route save(Route delivery);

    Route findByRouteId(Long routeId);

    int count();

    List<Route> findByArrivalWarehouseId(Long warehouseId);
}
