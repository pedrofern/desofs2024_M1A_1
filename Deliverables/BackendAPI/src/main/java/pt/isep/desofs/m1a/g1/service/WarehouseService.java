package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.CreateWarehouseDto;
import pt.isep.desofs.m1a.g1.dto.UpdateWarehouseDto;
import pt.isep.desofs.m1a.g1.dto.WarehouseDto;

import java.util.List;
import java.util.Map;

public interface WarehouseService {
    WarehouseDto createWarehouse(CreateWarehouseDto warehouseDto);
    WarehouseDto updateWarehouse(Long identifier, UpdateWarehouseDto warehouseDto);
    WarehouseDto findWarehouseByIdentifier(Long identifier);
    List<WarehouseDto> findAllWarehouses();
    List<WarehouseDto> findWarehouses(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);
}
