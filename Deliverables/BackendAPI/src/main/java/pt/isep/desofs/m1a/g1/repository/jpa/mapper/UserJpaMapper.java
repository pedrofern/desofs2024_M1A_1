package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {
	
	UserJpaMapper INSTANCE = Mappers.getMapper(UserJpaMapper.class);

	UserJpa toDatabaseEntity(User user);

	User toDomainModel(UserJpa user);

}
