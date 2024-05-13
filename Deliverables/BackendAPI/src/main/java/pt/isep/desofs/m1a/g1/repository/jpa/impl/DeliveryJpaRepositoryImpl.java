package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.DeliveryJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.DeliveryJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class DeliveryJpaRepositoryImpl implements DeliveryRepository {

    @Autowired
    private DeliveryJpaRepo repo;

    private final DeliveryJpaMapper mapper = DeliveryJpaMapper.INSTANCE;

    @Override
    public List<Delivery> findAll() {
        return repo.findAll().stream()
                .map(mapper::deliveryJpaToDelivery)
                .collect(Collectors.toList());
    }

    @Override
    public Delivery findByIdentifier(Long identifier) {
        Optional<DeliveryJpa> deliveryJpa = repo.findByIdentifier(identifier);
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
        return repo.existsByIdentifier(identifier);
    }

    @Override
    public void deleteByIdentifier(Long identifier) {
        repo.deleteByIdentifier(identifier);
    }
}
