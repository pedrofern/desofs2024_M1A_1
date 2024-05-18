package pt.isep.desofs.m1a.g1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.*;
import pt.isep.desofs.m1a.g1.exception.InvalidLocalizationFormatException;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.warehouse.Address;
import pt.isep.desofs.m1a.g1.model.warehouse.GeographicCoordinates;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.service.WarehouseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    private GeolocalizacaoService geoService;

    @Override
    public WarehouseDto createWarehouse(CreateWarehouseDto warehouseDto) {

        GeolocalizacaoResponseDTO response = geoService.obterLocalizacao(warehouseDto.getLatitude(), warehouseDto.getLongitude());
        if (response == null) {
            throw new InvalidLocalizationFormatException("Invalid localization!");
        }

        Delivery delivery = deliveryRepository.findByIdentifier(1L);
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(delivery);


        Warehouse warehouse = convertToEntity(warehouseDto, response, deliveries);
        warehouse = warehouseRepository.save(warehouse);
        return convertToDto(warehouse);
    }

    @Override
    public WarehouseDto updateWarehouse(Long identifier, UpdateWarehouseDto warehouseDto) throws NotFoundException {
        Optional<Warehouse> existingWarehouse = warehouseRepository.findByIdentifier(identifier);
        if (existingWarehouse.isEmpty()) {
            throw new NotFoundException("Warehouse not found with identifier: " + identifier);
        }
        // Update the entity fields here
        existingWarehouse.get().changeDesignation(warehouseDto.getDesignation());
        existingWarehouse.get().getAddress().changeStreetName(warehouseDto.getStreetName());
        existingWarehouse.get().getAddress().changeDoorNumber(warehouseDto.getDoorNumber());
        existingWarehouse.get().getAddress().changeCity(warehouseDto.getCity());
        existingWarehouse.get().getAddress().changeCountry(warehouseDto.getCountry());
        existingWarehouse.get().getAddress().changeZipCode(warehouseDto.getZipCode());
        existingWarehouse.get().getGeographicCoordinates().changeLatitude(warehouseDto.getLatitude());
        existingWarehouse.get().getGeographicCoordinates().changeLongitude(warehouseDto.getLongitude());
        existingWarehouse.get().changeActive(warehouseDto.isActive());
        Warehouse result = warehouseRepository.save(existingWarehouse.get());
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

    private Warehouse convertToEntity(CreateWarehouseDto warehouseDto, GeolocalizacaoResponseDTO response, List<Delivery> deliveries) {
        return new Warehouse(warehouseDto.getIdentifier(), warehouseDto.getDesignation(), response.getRua(), response.getN_porta(),
                response.getFreguesia(),response.getDistrito(), "4410-123", warehouseDto.getLatitude(),
                warehouseDto.getLongitude(), true, null);
    }

    private WarehouseDto convertToDto(Warehouse warehouse) {
        return new WarehouseDto(warehouse.getIdentifier(), warehouse.getDesignation(), warehouse.getAddress().getStreetName(), warehouse.getAddress().getDoorNumber(),
                warehouse.getAddress().getCity(), warehouse.getAddress().getCountry(), warehouse.getAddress().getZipCode(),
                warehouse.getGeographicCoordinates().getLatitude(), warehouse.getGeographicCoordinates().getLongitude(), warehouse.isActive());
    }
}



