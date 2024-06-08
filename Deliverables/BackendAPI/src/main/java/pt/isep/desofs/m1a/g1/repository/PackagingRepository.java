package pt.isep.desofs.m1a.g1.repository;

import org.springframework.data.domain.Page;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PackagingRepository {

    List<Packaging> findAll();

    Page<Packaging> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);

    void save(Packaging user);

    Optional<Packaging> findByPackagingId(String id);

    List<Packaging> findByDelivery(Delivery delivery);
}
