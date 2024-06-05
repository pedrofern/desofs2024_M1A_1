package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryPlanJpa;

import java.util.UUID;

public interface DeliveryPlanJpaRepo extends JpaRepository<DeliveryPlanJpa, UUID>, JpaSpecificationExecutor<DeliveryPlanJpa> {

    DeliveryPlanJpa findByDeliveryPlanId(Long id);
}
