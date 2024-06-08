package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserExtensionJpa;

@Mapper
public interface UserExtensionJpaMapper {

	UserExtensionJpaMapper INSTANCE = Mappers.getMapper(UserExtensionJpaMapper.class);

	@Mapping(target = "id", ignore = true)
	void updateUserExtensionJpa(@MappingTarget UserExtensionJpa userJpa, UserExtension user);
	
	@Mapping(target = "id", ignore = true)
	UserExtensionJpa toDatabaseEntity(UserExtension user);

	UserExtension toDomainModel(UserExtensionJpa user);

}
