package pt.isep.desofs.m1a.g1.repository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;

import java.util.List;
import java.util.Map;

@Repository
public interface DeliveryRepository {

    List<Delivery> findAll();

    Page<Delivery> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);

    Delivery findByDeliveryId(Long deliveryId);

    Delivery create(Delivery delivery);

    Delivery update(Delivery delivery);

    boolean existsByIdentifier(Long identifier);

    void deleteByIdentifier(Long identifier);

    Long getNextSequenceValue();

    List<Delivery> findByDeliveryDateAndWarehouseId(String deliveryDate, Long warehouseId);
}
