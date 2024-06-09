package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pt.isep.desofs.m1a.g1.model.monitoring.Monitoring;
import pt.isep.desofs.m1a.g1.repository.jpa.model.MonitoringJpa;

@Mapper
public interface MonitoringJpaMapper {

	MonitoringJpaMapper INSTANCE = Mappers.getMapper(MonitoringJpaMapper.class);

	@org.mapstruct.Mapping(target = "id", ignore = true)
	MonitoringJpa toDatabaseEntity(Monitoring monitoring);

}
