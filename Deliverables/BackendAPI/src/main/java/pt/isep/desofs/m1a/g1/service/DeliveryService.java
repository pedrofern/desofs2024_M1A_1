package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDTO> findAllDeliveries();
    DeliveryDTO findDeliveryByDeliveryId(Long deliveryId) throws NotFoundException;
    DeliveryDTO createDelivery(CreateDeliveryDTO deliveryDTO);
    DeliveryDTO updateDelivery(Long deliveryId, DeliveryDTO deliveryDTO) throws NotFoundException;
    void deleteDelivery(Long deliveryId) throws NotFoundException;
}
