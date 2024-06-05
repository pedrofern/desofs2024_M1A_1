package pt.isep.desofs.m1a.g1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;

import java.util.List;

@Repository
public interface DeliveryRepository {

    List<Delivery> findAll();

    Page<Delivery> findAllWithFilters(Specification<DeliveryJpa> specification, Pageable pageable);

    Delivery findByDeliveryId(Long deliveryId);

    Delivery create(Delivery delivery);

    Delivery update(Delivery delivery);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);

    Long getNextSequenceValue();

    List<Delivery> findByDeliveryDateAndWarehouseId(String deliveryDate, Long warehouseId);
}
