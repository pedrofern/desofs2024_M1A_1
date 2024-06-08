package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.logistics.Time;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;


@Mapper(uses = TimeJpaMapper.class)
public interface PackagingJpaMapper {

    PackagingJpaMapper INSTANCE = Mappers.getMapper(PackagingJpaMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loadTime", source = "loadTime.value")
    @Mapping(target = "unloadTime", source = "unloadTime.value")
    @Mapping(target = "x", source = "localization.x")
    @Mapping(target = "y", source = "localization.y")
    @Mapping(target = "z", source = "localization.z")
    @Mapping(target = "delivery", ignore = true)
    @Mapping(target = "truck", ignore = true)
    PackagingJpa toDatabaseEntity(Packaging packaging);

    @Mapping(target = "deliveryId", source = "delivery.deliveryId")
    @Mapping(target = "truckId", source = "truck.truckId")
    Packaging  toDomainModel(PackagingJpa packagingJpa);
}
