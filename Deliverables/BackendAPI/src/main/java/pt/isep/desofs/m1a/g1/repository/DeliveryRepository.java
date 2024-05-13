package pt.isep.desofs.m1a.g1.repository;

import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;

import java.util.List;

@Repository
public interface DeliveryRepository {

    List<Delivery> findAll();

    Delivery findByIdentifier(Long identifier);

    Delivery save(Delivery delivery);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);
}
