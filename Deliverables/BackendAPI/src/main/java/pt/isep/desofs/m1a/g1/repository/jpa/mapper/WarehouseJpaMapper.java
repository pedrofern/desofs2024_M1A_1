package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import lombok.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;


@Mapper
public interface WarehouseJpaMapper {

    WarehouseJpaMapper INSTANCE = Mappers.getMapper(WarehouseJpaMapper.class);

    @Mapping(target = "streetName", source = "address.streetName")
    @Mapping(target = "doorNumber", source = "address.doorNumber")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "country", source = "address.country")
    @Mapping(target = "zipCode", source = "address.zipCode")
    @Mapping(target = "latitude", source = "geographicCoordinates.latitude")
    @Mapping(target = "longitude", source = "geographicCoordinates.longitude")
    WarehouseJpa toDatabaseEntity(Warehouse warehouse);

    Warehouse  toDomainModel(WarehouseJpa warehouseJpa);
}
