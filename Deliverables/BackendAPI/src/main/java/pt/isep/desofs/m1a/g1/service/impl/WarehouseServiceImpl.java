package pt.isep.desofs.m1a.g1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.*;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.service.WarehouseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    @Autowired
    private GeoLocalizationService geoService;

    @Override
    public WarehouseDto createWarehouse(CreateWarehouseDto warehouseDto) {

        Warehouse warehouse;
        GeoLocalizationResponseDTO response = geoService.getGeoLocalization(warehouseDto.getLatitude(), warehouseDto.getLongitude());
        if (response == null) {
            warehouse = convertToEntity(warehouseDto);
        } else {
            warehouse = convertToEntity(warehouseDto, response);
        }
        warehouse = warehouseRepository.save(warehouse);
        return convertToDto(warehouse);
    }

    @Override
    public WarehouseDto updateWarehouse(Long identifier, UpdateWarehouseDto warehouseDto) throws NotFoundException {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findByIdentifier(identifier);
        if (existingWarehouse.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + identifier);
        }
        existingWarehouse.get().changeDesignation(warehouseDto.getDesignation());
        existingWarehouse.get().getAddress().changeStreetName(warehouseDto.getStreetName());
        existingWarehouse.get().getAddress().changeDoorNumber(warehouseDto.getDoorNumber());
        existingWarehouse.get().getAddress().changeCity(warehouseDto.getCity());
        existingWarehouse.get().getAddress().changeCountry(warehouseDto.getCountry());
        existingWarehouse.get().getAddress().changeZipCode(warehouseDto.getZipCode());
        existingWarehouse.get().getGeographicCoordinates().changeLatitude(warehouseDto.getLatitude());
        existingWarehouse.get().getGeographicCoordinates().changeLongitude(warehouseDto.getLongitude());
        existingWarehouse.get().changeActive(warehouseDto.isActive());
        Warehouse result = warehouseRepository.update(existingWarehouse.get());
        return convertToDto(result);
    }

    @Override
    public WarehouseDto findWarehouseByIdentifier(Long identifier) throws NotFoundException {
        Optional<Warehouse> warehouse = warehouseRepository.findByIdentifier(identifier);
        if (warehouse.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + identifier);
        }
        return convertToDto(warehouse.get());
    }

    @Override
    public List<WarehouseDto> findAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WarehouseDto> findWarehouses(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        Page<Warehouse> page = warehouseRepository.findAllWithFilters(pageIndex, pageSize, sortBy, sortOrder, filters);
        return page.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Warehouse convertToEntity(CreateWarehouseDto warehouseDto) {
        return new Warehouse(warehouseDto.getIdentifier(), warehouseDto.getDesignation(), "", "",
                "", "", "", warehouseDto.getLatitude(),
                warehouseDto.getLongitude(), true);
    }

    private Warehouse convertToEntity(CreateWarehouseDto warehouseDto, GeoLocalizationResponseDTO response) {
        return new Warehouse(warehouseDto.getIdentifier(), warehouseDto.getDesignation(), response.getRua(), response.getNporta(),
                response.getFreguesia(), response.getDistrito(), response.getCp() == null ? "4420-123" : response.getCp(), warehouseDto.getLatitude(),
                warehouseDto.getLongitude(), true);
    }

    private WarehouseDto convertToDto(Warehouse warehouse) {
        return new WarehouseDto(warehouse.getIdentifier(), warehouse.getDesignation(), warehouse.getAddress().getStreetName(), warehouse.getAddress().getDoorNumber(),
                warehouse.getAddress().getCity(), warehouse.getAddress().getCountry(), warehouse.getAddress().getZipCode(),
                warehouse.getGeographicCoordinates().getLatitude(), warehouse.getGeographicCoordinates().getLongitude(), warehouse.isActive());
    }
}



