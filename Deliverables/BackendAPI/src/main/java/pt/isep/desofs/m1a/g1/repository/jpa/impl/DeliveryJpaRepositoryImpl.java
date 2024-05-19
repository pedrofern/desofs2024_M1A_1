package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
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
    public Delivery findByIdentifier(Long identifier) {
        Optional<DeliveryJpa> deliveryJpa = repo.findByDeliveryId(identifier);
        return deliveryJpa.map(mapper::deliveryJpaToDelivery).orElse(null);
    }

    @Override
    public Delivery save(Delivery delivery) {
        DeliveryJpa deliveryJpa = mapper.deliveryToDeliveryJpa(delivery);
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
}
