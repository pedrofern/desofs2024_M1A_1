package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TokenJpa;

@Mapper(uses = UserJpaMapper.class)
public interface TokenJpaMapper {

	TokenJpaMapper INSTANCE = Mappers.getMapper(TokenJpaMapper.class);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "user", ignore = true)
	void updateTokenJpa(@MappingTarget TokenJpa target, Token source);

	TokenJpa toDatabaseEntity(Token token);

	Token toDomainModel(TokenJpa token);

	List<TokenJpa> toDatabaseEntity(List<Token> token);

	List<Token> toDomainModel(List<TokenJpa> token);

}
