package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryJpaRepo extends JpaRepository<DeliveryJpa, UUID>, JpaSpecificationExecutor<DeliveryJpa> {

    @Query(value = "SELECT NEXTVAL('deliveryId_seq')", nativeQuery = true)
    Long getNextSequenceValue();

    Optional<DeliveryJpa> findByDeliveryId(Long identifier);

    List<DeliveryJpa> findByDeliveryIdAndWarehouse_Identifier(Long deliveryId, Long warehouseId);

    boolean existsByDeliveryId(Long identifier);

    void deleteByDeliveryId(Long identifier);
}
