package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isep.desofs.m1a.g1.dto.CreateWarehouseDto;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.UpdateWarehouseDto;
import pt.isep.desofs.m1a.g1.dto.WarehouseDto;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.service.WarehouseService;

import java.util.List;

@Tag(name = "Warehouse")
@RestController
@RequestMapping(path = "/api/v1/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/")
    public ResponseEntity<WarehouseDto> createWarehouse(@RequestBody CreateWarehouseDto body) {
        try {
            WarehouseDto savedWarehouse = warehouseService.createWarehouse(body);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedWarehouse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{identifier}")
    public ResponseEntity<WarehouseDto> editWarehouse(@PathVariable Long identifier, @RequestBody UpdateWarehouseDto body) {
        try {
            WarehouseDto updatedWarehouse = warehouseService.updateWarehouse(identifier, body);
            return ResponseEntity.ok(updatedWarehouse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<WarehouseDto> getDeliveryById(@PathVariable Long identifier) {
        try {
            WarehouseDto warehouse = warehouseService.findWarehouseByIdentifier(identifier);
            return ResponseEntity.ok(warehouse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDto>> getAllDeliveries() {
        try {
            List<WarehouseDto> warehouses = warehouseService.findAllWarehouses();
            return ResponseEntity.ok(warehouses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
