package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

@Mapper
public interface TruckJpaMapper {

    TruckJpaMapper INSTANCE = Mappers.getMapper(TruckJpaMapper.class);

    TruckJpa toDatabaseEntity(Truck truck);

    Truck toDomainModel(TruckJpa truckJpa);
}