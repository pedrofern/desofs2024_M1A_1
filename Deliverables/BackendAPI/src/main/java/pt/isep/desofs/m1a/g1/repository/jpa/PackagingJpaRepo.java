package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PackagingJpaRepo extends JpaRepository<PackagingJpa, UUID>, JpaSpecificationExecutor<PackagingJpa> {

    PackagingJpa findByPackagingId(String packagingId);

    List<PackagingJpa> findByDelivery_DeliveryId(long deliveryId);
}
