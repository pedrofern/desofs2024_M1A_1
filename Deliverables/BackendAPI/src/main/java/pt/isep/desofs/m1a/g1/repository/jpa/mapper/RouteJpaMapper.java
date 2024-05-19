package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.delivery.Route;
import pt.isep.desofs.m1a.g1.repository.jpa.model.RouteJpa;

@Mapper
public interface RouteJpaMapper {

	RouteJpaMapper INSTANCE = Mappers.getMapper(RouteJpaMapper.class);

	Route routeJpaToRoute(RouteJpa routeJpa);

	RouteJpa routeToRouteJpa(Route route);
}
