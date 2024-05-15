package pt.isep.desofs.m1a.g1.repository;

import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;

import java.util.List;
import java.util.Optional;

public interface PackagingRepository {

    List<Packaging> findAll();

    void save(Packaging user);

    Optional<Packaging> findByPackagingId(String id);

    List<Packaging> findByDeliveryId(long deliveryId);
}
