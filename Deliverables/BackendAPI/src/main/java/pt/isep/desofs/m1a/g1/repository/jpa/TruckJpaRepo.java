package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

import java.util.Optional;
import java.util.UUID;

public interface TruckJpaRepo extends JpaRepository<TruckJpa, UUID>, JpaSpecificationExecutor<TruckJpa> {

    Optional<TruckJpa> findByTruckId(long truckId);

}