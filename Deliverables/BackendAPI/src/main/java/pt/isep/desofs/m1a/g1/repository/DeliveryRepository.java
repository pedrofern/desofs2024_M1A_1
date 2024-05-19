package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;

import java.util.List;

@Repository
public interface DeliveryRepository {

    List<Delivery> findAll();

    Delivery findByDeliveryId(Long deliveryId);

    Delivery save(Delivery delivery);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);

    Long getNextSequenceValue();
}
