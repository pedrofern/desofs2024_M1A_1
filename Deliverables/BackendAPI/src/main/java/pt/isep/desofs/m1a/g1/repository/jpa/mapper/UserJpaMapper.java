package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

@Mapper
public interface UserJpaMapper {
	
	UserJpaMapper INSTANCE = Mappers.getMapper(UserJpaMapper.class);

	@Mapping(target = "email", source = "email.value")
	@Mapping(target = "phoneNumber", source = "phoneNumber.value")
	@Mapping(target = "firstName", source = "firstName.value")
	@Mapping(target = "lastName", source = "lastName.value")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "tokens", ignore = true)
	UserJpa toDatabaseEntity(User user);

	@Mapping(target = "authorities", ignore = true)
	User toDomainModel(UserJpa user);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "email", source = "email.value")
	@Mapping(target = "phoneNumber", source = "phoneNumber.value")
	@Mapping(target = "firstName", source = "firstName.value")
	@Mapping(target = "lastName", source = "lastName.value")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "tokens", ignore = true)
	@Mapping(target = "password", ignore = true)
	void updateUserJpa(@MappingTarget UserJpa userJpa, User user);
	
	List<User> toDomainModel(List<UserJpa> userList);

}
