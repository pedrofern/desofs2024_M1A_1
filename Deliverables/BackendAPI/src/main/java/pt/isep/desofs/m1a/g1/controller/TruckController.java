package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isep.desofs.m1a.g1.config.InputSanitizer;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.TruckDto;
import pt.isep.desofs.m1a.g1.exception.InvalidTruckException;
import pt.isep.desofs.m1a.g1.service.TruckService;

import java.util.List;
import java.util.Map;

@Tag(name = "Trucks")
@RestController
@RequestMapping(path = "/api/v1/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @PostMapping("/")
    public ResponseEntity<TruckDto> createTruck(@RequestBody TruckDto truckDto) {
        try {
            TruckDto createdTruck = truckService.createTruck(truckDto);
            return new ResponseEntity<>(createdTruck, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{truckId}")
    public ResponseEntity<TruckDto> updateTruck(@PathVariable long truckId, @RequestBody TruckDto truckDto) {
        try {
            TruckDto updatedTruck = truckService.updateTruck(truckId, truckDto);
            return new ResponseEntity<>(updatedTruck, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TruckDto>> getAllTrucks() {
        try {
            List<TruckDto> trucks = truckService.getAllTrucks();
            return new ResponseEntity<>(trucks, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TruckDto>> getTrucks(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "truckId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) Map<String, String> filters
    ) {
        try {
            List<TruckDto> trucks = truckService.getTrucks(pageIndex, pageSize, sortBy, sortOrder, filters);
            return ResponseEntity.ok(trucks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<TruckDto>> getActiveTrucks() {
        try {
            List<TruckDto> trucks = truckService.getActiveTrucks();
            return new ResponseEntity<>(trucks, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{truckId}")
    public ResponseEntity<TruckDto> getTruck(@PathVariable long truckId) {
        try {
            TruckDto truck = truckService.getTruck(truckId);
            return new ResponseEntity<>(truck, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}