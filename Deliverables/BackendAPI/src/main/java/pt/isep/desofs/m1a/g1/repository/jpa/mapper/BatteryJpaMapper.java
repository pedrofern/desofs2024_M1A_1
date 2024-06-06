package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.repository.jpa.model.BatteryJpa;

@Mapper
public interface BatteryJpaMapper {

    BatteryJpaMapper INSTANCE = Mappers.getMapper(BatteryJpaMapper.class);

    @Mapping(target = "id", ignore = true)
    BatteryJpa toDatabaseEntity(Battery battery);

    Battery toDomainModel(BatteryJpa batteryJpa);
}
