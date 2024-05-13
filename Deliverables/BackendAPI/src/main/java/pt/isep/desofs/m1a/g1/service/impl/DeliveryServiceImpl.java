package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<DeliveryDTO> findAllDeliveries() {
        return deliveryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryDTO findDeliveryByIdentifier(Long identifier) throws NotFoundException {
        Delivery delivery = deliveryRepository.findByIdentifier(identifier);
        if (delivery == null) {
            throw new NotFoundException("Delivery not found with identifier: " + identifier);
        }
        return convertToDTO(delivery);
    }

    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = convertToEntity(deliveryDTO);
        delivery = deliveryRepository.save(delivery);
        return convertToDTO(delivery);
    }

    @Override
    public DeliveryDTO updateDelivery(Long identifier, DeliveryDTO deliveryDTO) throws NotFoundException {
        Delivery existingDelivery = deliveryRepository.findByIdentifier(identifier);
        if (existingDelivery == null) {
            throw new NotFoundException("Delivery not found with identifier: " + identifier);
        }
        // Update the entity fields here
        existingDelivery.setDeliveryDate(deliveryDTO.getDeliveryDate());
        existingDelivery.setWeight(deliveryDTO.getWeight());
        existingDelivery.setWarehouseId(deliveryDTO.getWarehouseId());
        existingDelivery = deliveryRepository.save(existingDelivery);
        return convertToDTO(existingDelivery);
    }

    @Override
    public void deleteDelivery(Long identifier) throws NotFoundException {
        if (!deliveryRepository.existsByIdentifier(identifier)) {
            throw new NotFoundException("Delivery not found with identifier: " + identifier);
        }
        deliveryRepository.deleteByIdentifier(identifier);
    }

    private DeliveryDTO convertToDTO(Delivery delivery) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setIdentifier(delivery.getIdentifier());
        dto.setDeliveryDate(delivery.getDeliveryDate());
        dto.setWeight(delivery.getWeight());
        dto.setWarehouseId(delivery.getWarehouseId());
        return dto;
    }

    private Delivery convertToEntity(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setIdentifier(deliveryDTO.getIdentifier());
        delivery.setDeliveryDate(deliveryDTO.getDeliveryDate());
        delivery.setWeight(deliveryDTO.getWeight());
        delivery.setWarehouseId(deliveryDTO.getWarehouseId());
        return delivery;
    }
}
