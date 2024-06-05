package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.model.RouteJpa;

import java.util.List;
import java.util.UUID;

public interface RouteJpaRepo extends JpaRepository<RouteJpa, UUID> {

    List<RouteJpa> findByArrivalWarehouse_IdentifierOrDepartureWarehouse_Identifier(Long warehouseA, Long warehouseD);
}
