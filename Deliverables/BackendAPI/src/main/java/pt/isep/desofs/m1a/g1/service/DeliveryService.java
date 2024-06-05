package pt.isep.desofs.m1a.g1.service;

import org.springframework.data.domain.Page;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.UpdateDeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;

import java.util.List;
import java.util.Map;

public interface DeliveryService {
    List<DeliveryDTO> getAllDeliveries();
    List<DeliveryDTO> getDeliveries(int page, int size, String sortField, String sortDirection, Map<String, String> filters);
    DeliveryDTO findDeliveryByDeliveryId(Long deliveryId) throws NotFoundException;
    DeliveryDTO createDelivery(CreateDeliveryDTO deliveryDTO);
    DeliveryDTO updateDelivery(Long deliveryId, UpdateDeliveryDTO deliveryDTO) throws NotFoundException;
    void deleteDelivery(Long deliveryId) throws NotFoundException;
}
