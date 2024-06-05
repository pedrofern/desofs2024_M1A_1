package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.repository.DeliveryPlanRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.DeliveryPlanJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.DeliveryPlanJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryPlanJpa;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class DeliveryPlanJpaRepositoryImpl implements DeliveryPlanRepository {

    @Autowired
    private DeliveryPlanJpaRepo repo;

    private final DeliveryPlanJpaMapper mapper = DeliveryPlanJpaMapper.INSTANCE;

    @Override
    public List<DeliveryPlan> findAll() {
        return repo.findAll().stream()
                .map(mapper::deliveryPlanJpaToDeliveryPlan)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryPlan findByDeliveryPlanId(Long id) {
        return mapper.deliveryPlanJpaToDeliveryPlan(repo.findByDeliveryPlanId(id));
    }

    @Override
    public DeliveryPlan save(DeliveryPlan deliveryPlan) {
        DeliveryPlanJpa deliveryJpa = mapper.deliveryPlanToDeliveryPlanJpa(deliveryPlan);
        DeliveryPlanJpa savedDeliveryPlanJpa = repo.save(deliveryJpa);
        return mapper.deliveryPlanJpaToDeliveryPlan(savedDeliveryPlanJpa);
    }
}
