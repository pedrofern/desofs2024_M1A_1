package pt.isep.desofs.m1a.g1.service.impl;

import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.RouteDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;

public interface RouteServiceImpl {
    DeliveryDTO findDeliveryByRouteId(Long routeId) throws NotFoundException;
    DeliveryDTO createRoute(RouteDTO routeDTO);
}
