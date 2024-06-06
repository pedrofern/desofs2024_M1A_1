package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;

@Mapper
public interface DeliveryJpaMapper {

	DeliveryJpaMapper INSTANCE = Mappers.getMapper(DeliveryJpaMapper.class);

	@Mapping(source = "warehouse.identifier", target = "warehouseId")
	Delivery deliveryJpaToDelivery(DeliveryJpa deliveryJpa);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "warehouse", ignore = true)
	DeliveryJpa deliveryToDeliveryJpa(Delivery delivery);
}
