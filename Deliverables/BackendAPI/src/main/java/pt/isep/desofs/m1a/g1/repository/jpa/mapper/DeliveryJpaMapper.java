package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;

@Mapper
public interface DeliveryJpaMapper {

	DeliveryJpaMapper INSTANCE = Mappers.getMapper(DeliveryJpaMapper.class);

	DeliveryJpa deliveryToDeliveryJpa(Delivery delivery);
	Delivery deliveryJpaToDelivery(DeliveryJpa deliveryJpa);
}
