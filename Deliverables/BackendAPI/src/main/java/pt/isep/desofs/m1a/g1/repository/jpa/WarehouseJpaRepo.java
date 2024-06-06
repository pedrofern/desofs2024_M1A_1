package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseJpaRepo extends JpaRepository<WarehouseJpa, UUID>, JpaSpecificationExecutor<WarehouseJpa> {

    Optional<WarehouseJpa> findByIdentifier(Long identifier);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);
}
