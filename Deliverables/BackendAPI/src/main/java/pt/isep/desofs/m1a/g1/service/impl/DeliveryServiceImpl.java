package pt.isep.desofs.m1a.g1.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.utils.SpecificationHelper;
import pt.isep.desofs.m1a.g1.service.DeliveryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        return deliveries.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DeliveryDTO> getDeliveries(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        this.removePageFilters(filters);
        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortBy));
        Specification<DeliveryJpa> specification = SpecificationHelper.getSpecifications(filters);
        Page<Delivery> page = deliveryRepository.findAllWithFilters(specification, pageable);
        return page.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DeliveryDTO findDeliveryByDeliveryId(Long deliveryId) throws NotFoundException {
        Delivery delivery = deliveryRepository.findByDeliveryId(deliveryId);
        if (delivery == null) {
            throw new NotFoundException("Delivery not found with identifier: " + deliveryId);
        }
        return convertToDTO(delivery);
    }

    @Override
    @Transactional
    public DeliveryDTO createDelivery(CreateDeliveryDTO deliveryDTO) {
        Delivery delivery = convertToEntity(deliveryDTO);
        delivery.setDeliveryId(deliveryRepository.getNextSequenceValue());
        delivery = deliveryRepository.save(delivery);
        return convertToDTO(delivery);
    }

    @Override
    @Transactional
    public DeliveryDTO updateDelivery(Long deliveryId, DeliveryDTO deliveryDTO) throws NotFoundException {
        Delivery existingDelivery = deliveryRepository.findByDeliveryId(deliveryId);
        if (existingDelivery == null) {
            throw new NotFoundException("Delivery not found with identifier: " + deliveryId);
        }
        // Update the entity fields here
        existingDelivery.setDeliveryDate(deliveryDTO.getDeliveryDate());
        existingDelivery.setWeight(deliveryDTO.getWeight());
        existingDelivery = deliveryRepository.save(existingDelivery);
        return convertToDTO(existingDelivery);
    }

    @Override
    @Transactional
    public void deleteDelivery(Long deliveryId) throws NotFoundException {
        if (!deliveryRepository.existsByIdentifier(deliveryId)) {
            throw new NotFoundException("Delivery not found with identifier: " + deliveryId);
        }
        deliveryRepository.deleteByIdentifier(deliveryId);
    }

    private DeliveryDTO convertToDTO(Delivery delivery) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setDeliveryId(delivery.getDeliveryId());
        dto.setDeliveryDate(delivery.getDeliveryDate());
        dto.setWeight(delivery.getWeight());
        dto.setWarehouseId(delivery.getWarehouseId());
        return dto;
    }

    private Delivery convertToEntity(CreateDeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(deliveryDTO.getDeliveryDate());
        delivery.setWeight(deliveryDTO.getWeight());
        delivery.setWarehouseId(deliveryDTO.getWarehouseId());
        return delivery;
    }

    private void removePageFilters(Map<String, String> filters) {
        filters.remove("pageSize");
        filters.remove("pageIndex");
        filters.remove("sortBy");
        filters.remove("sortOrder");
    }
}
