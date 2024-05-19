package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.model.RouteJpa;

import java.util.Optional;
import java.util.UUID;

public interface RouteJpaRepo extends JpaRepository<RouteJpa, UUID> {

    Optional<RouteJpa> findByRouteId(Long routeId);
}
