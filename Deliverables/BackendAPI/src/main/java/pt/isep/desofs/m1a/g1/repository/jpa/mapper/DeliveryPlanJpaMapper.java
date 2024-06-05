package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryPlanJpa;

@Mapper
public interface DeliveryPlanJpaMapper {

    DeliveryPlanJpaMapper INSTANCE = Mappers.getMapper(DeliveryPlanJpaMapper.class);

    @Mapping(source = "deliveryPlanId", target = "deliveryPlanId")
    @Mapping(source = "routes", target = "routes")
    @Mapping(source = "deliveries", target = "deliveries")
    DeliveryPlan deliveryPlanJpaToDeliveryPlan(DeliveryPlanJpa deliveryJpa);

    @Mapping(source = "deliveryPlanId", target = "deliveryPlanId")
    @Mapping(source = "routes", target = "routes")
    @Mapping(source = "deliveries", target = "deliveries")
    DeliveryPlanJpa deliveryPlanToDeliveryPlanJpa(DeliveryPlan delivery);
}
