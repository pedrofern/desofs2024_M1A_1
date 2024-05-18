package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.WarehouseDto;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;

import java.util.List;

public interface WarehouseService {
    WarehouseDto createWarehouse(WarehouseDto warehouseDto);
}
