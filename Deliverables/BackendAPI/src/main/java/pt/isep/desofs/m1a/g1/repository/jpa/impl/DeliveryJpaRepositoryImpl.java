package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.DeliveryJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.DeliveryJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;
import pt.isep.desofs.m1a.g1.repository.utils.SpecificationHelper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class DeliveryJpaRepositoryImpl implements DeliveryRepository {

    @Autowired
    private DeliveryJpaRepo repo;

    @Autowired
    private WarehouseJpaRepo wRepo;

    private final DeliveryJpaMapper mapper = DeliveryJpaMapper.INSTANCE;
    @Autowired
    private DeliveryJpaRepo deliveryJpaRepo;

    @Override
    public List<Delivery> findAll() {
        return repo.findAll().stream()
                .map(mapper::deliveryJpaToDelivery)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Delivery> findAllWithFilters(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        SpecificationHelper.removePageFilters(filters);
        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortBy));
        Specification<DeliveryJpa> specification = SpecificationHelper.getSpecifications(filters);
        return repo.findAll(specification, pageable).map(mapper::deliveryJpaToDelivery);
    }

    @Override
    public Delivery findByDeliveryId(Long deliveryId) {
        Optional<DeliveryJpa> deliveryJpa = repo.findByDeliveryId(deliveryId);
        return deliveryJpa.map(mapper::deliveryJpaToDelivery).orElse(null);
    }

    @Override
    public Delivery create(Delivery delivery) {
        Optional<WarehouseJpa> warehouseJpa = wRepo.findByIdentifier(delivery.getWarehouseId());
        if (warehouseJpa.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + delivery.getWarehouseId());
        }
        DeliveryJpa deliveryJpa = mapper.deliveryToDeliveryJpa(delivery);
        deliveryJpa.setWarehouse(warehouseJpa.get());
        DeliveryJpa savedDeliveryJpa = repo.save(deliveryJpa);
        return mapper.deliveryJpaToDelivery(savedDeliveryJpa);
    }

    @Override
    public Delivery update(Delivery delivery) {
        Optional<WarehouseJpa> warehouseJpa = wRepo.findByIdentifier(delivery.getWarehouseId());
        if (warehouseJpa.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + delivery.getWarehouseId());
        }
        Optional<DeliveryJpa> deliveryJpaOptional = deliveryJpaRepo.findByDeliveryId(delivery.getDeliveryId());
        if (deliveryJpaOptional.isEmpty()) {
            throw new NotFoundException("Delivery not found with identifier: " + delivery.getDeliveryId());
        }
        DeliveryJpa existingDeliveryJpa = deliveryJpaOptional.get();
        existingDeliveryJpa.setDeliveryDate(delivery.getDeliveryDate());
        existingDeliveryJpa.setWeight(delivery.getWeight());
        existingDeliveryJpa.setWarehouse(warehouseJpa.get());

        DeliveryJpa savedDeliveryJpa = repo.save(existingDeliveryJpa);
        return mapper.deliveryJpaToDelivery(savedDeliveryJpa);
    }

    @Override
    public boolean existsByIdentifier(Long identifier) {
        return repo.existsByDeliveryId(identifier);
    }

    @Override
    public void deleteByIdentifier(Long identifier) {
        repo.deleteByDeliveryId(identifier);
    }

    @Override
    public Long getNextSequenceValue() {
        return repo.getNextSequenceValue();
    }

    @Override
    public List<Delivery> findByDeliveryDateAndWarehouseId(String deliveryDate, Long warehouseId) {
        List<DeliveryJpa> deliveryJpa = repo.findByDeliveryDateAndWarehouse_Identifier(deliveryDate, warehouseId);
        return deliveryJpa.stream().map(mapper::deliveryJpaToDelivery).collect(Collectors.toList());
    }
}
