package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryJpaRepo extends JpaRepository<DeliveryJpa, UUID> {

    Optional<DeliveryJpa> findByIdentifier(Long identifier);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);
}
