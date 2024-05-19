package pt.isep.desofs.m1a.g1.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.service.DeliveryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

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
        Delivery delivery = deliveryRepository.findByDeliveryId(identifier);
        if (delivery == null) {
            throw new NotFoundException("Delivery not found with identifier: " + identifier);
        }
        return convertToDTO(delivery);
    }

    @Override
    @Transactional
    public DeliveryDTO createDelivery(CreateDeliveryDTO deliveryDTO) {
        Delivery delivery = convertToEntity(deliveryDTO);
        Optional<Warehouse> warehouse = warehouseRepository.findByIdentifier(delivery.getWarehouseId());
        if(warehouse.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + delivery.getWarehouseId());
        }
        delivery.setDeliveryId(deliveryRepository.getNextSequenceValue());
        delivery = deliveryRepository.save(delivery);
        return convertToDTO(delivery);
    }

    @Override
    @Transactional
    public DeliveryDTO updateDelivery(Long identifier, DeliveryDTO deliveryDTO) throws NotFoundException {
        Delivery existingDelivery = deliveryRepository.findByDeliveryId(identifier);
        if (existingDelivery == null) {
            throw new NotFoundException("Delivery not found with identifier: " + identifier);
        }
        // Update the entity fields here
        existingDelivery.setDeliveryDate(deliveryDTO.getDeliveryDate());
        existingDelivery.setWeight(deliveryDTO.getWeight());
        existingDelivery = deliveryRepository.save(existingDelivery);
        return convertToDTO(existingDelivery);
    }

    @Override
    @Transactional
    public void deleteDelivery(Long identifier) throws NotFoundException {
        if (!deliveryRepository.existsByIdentifier(identifier)) {
            throw new NotFoundException("Delivery not found with identifier: " + identifier);
        }
        deliveryRepository.deleteByIdentifier(identifier);
    }

    private DeliveryDTO convertToDTO(Delivery delivery) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setDeliveryId(delivery.getDeliveryId());
        dto.setDeliveryDate(delivery.getDeliveryDate());
        dto.setWeight(delivery.getWeight());
        return dto;
    }

    private Delivery convertToEntity(CreateDeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(deliveryDTO.getDeliveryDate());
        delivery.setWeight(deliveryDTO.getWeight());
        delivery.setWarehouseId(deliveryDTO.getWarehouseId());
        return delivery;
    }
}
