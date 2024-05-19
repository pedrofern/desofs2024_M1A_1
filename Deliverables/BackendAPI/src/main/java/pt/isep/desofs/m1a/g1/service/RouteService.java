package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.RouteDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;

public interface RouteService {
    DeliveryDTO findDeliveryByRouteId(Long routeId) throws NotFoundException;
    DeliveryDTO createRoute(RouteDTO routeDTO);
}
