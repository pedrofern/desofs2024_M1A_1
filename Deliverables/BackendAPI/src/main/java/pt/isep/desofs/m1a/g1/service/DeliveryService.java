package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;

import java.util.List;

public interface DeliveryService {
    List<DeliveryDTO> findAllDeliveries();
    DeliveryDTO findDeliveryByIdentifier(Long identifier) throws NotFoundException;
    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);
    DeliveryDTO updateDelivery(Long identifier, DeliveryDTO deliveryDTO) throws NotFoundException;
    void deleteDelivery(Long identifier) throws NotFoundException;
}
