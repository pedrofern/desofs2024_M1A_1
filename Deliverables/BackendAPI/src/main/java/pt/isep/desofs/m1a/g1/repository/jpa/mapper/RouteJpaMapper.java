package pt.isep.desofs.m1a.g1.repository.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pt.isep.desofs.m1a.g1.model.delivery.Route;
import pt.isep.desofs.m1a.g1.repository.jpa.model.RouteJpa;

@Mapper
public interface RouteJpaMapper {

    RouteJpaMapper INSTANCE = Mappers.getMapper(RouteJpaMapper.class);

    @Mapping(source = "routeId", target = "routeId")
    @Mapping(source = "departureWarehouse.identifier", target = "idDepartureWarehouse")
    @Mapping(source = "arrivalWarehouse.identifier", target = "idArrivalWarehouse")
    @Mapping(source = "distance", target = "distance")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "energy", target = "energy")
    @Mapping(source = "extraTime", target = "extraTime")
    Route routeJpaToRoute(RouteJpa routeJpa);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "routeId", target = "routeId")
    @Mapping(source = "idDepartureWarehouse", target = "departureWarehouse.identifier")
    @Mapping(source = "idArrivalWarehouse", target = "arrivalWarehouse.identifier")
    @Mapping(source = "distance", target = "distance")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "energy", target = "energy")
    @Mapping(source = "extraTime", target = "extraTime")
    RouteJpa routeToRouteJpa(Route route);
}
