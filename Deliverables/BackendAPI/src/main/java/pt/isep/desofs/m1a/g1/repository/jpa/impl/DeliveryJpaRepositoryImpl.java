package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.DeliveryJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.DeliveryJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;

import java.util.List;
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

    @Override
    public List<Delivery> findAll() {
        return repo.findAll().stream()
                .map(mapper::deliveryJpaToDelivery)
                .collect(Collectors.toList());
    }

    @Override
    public Delivery findByDeliveryId(Long deliveryId) {
        Optional<DeliveryJpa> deliveryJpa = repo.findByDeliveryId(deliveryId);
        return deliveryJpa.map(mapper::deliveryJpaToDelivery).orElse(null);
    }

    @Override
    public Delivery save(Delivery delivery) {
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
    public List<Delivery> findByDeliveryIdAndWarehouseId(Long deliveryId, Long warehouseId) {
        List<DeliveryJpa> deliveryJpa = repo.findByDeliveryIdAndWarehouse_Identifier(deliveryId, warehouseId);
        return deliveryJpa.stream().map(mapper::deliveryJpaToDelivery).collect(Collectors.toList());
    }
}
