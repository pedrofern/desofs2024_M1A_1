package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isep.desofs.m1a.g1.config.InputSanitizer;
import pt.isep.desofs.m1a.g1.dto.TruckDto;
import pt.isep.desofs.m1a.g1.exception.InvalidTruckException;
import pt.isep.desofs.m1a.g1.service.TruckService;

import java.util.List;

@Tag(name = "Trucks")
@RestController
@RequestMapping(path = "/api/v1/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @PostMapping
    public ResponseEntity<TruckDto> createTruck(@RequestBody TruckDto truckDto) {
        TruckDto createdTruck = truckService.createTruck(truckDto);
        return new ResponseEntity<>(createdTruck, HttpStatus.CREATED);
    }

    @PutMapping("/{truckId}")
    public ResponseEntity<TruckDto> updateTruck(@PathVariable long truckId, @RequestBody TruckDto truckDto) {
        TruckDto updatedTruck = truckService.updateTruck(truckId, truckDto);
        return new ResponseEntity<>(updatedTruck, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TruckDto>> getAllTrucks() {
        List<TruckDto> trucks = truckService.getAllTrucks();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TruckDto>> getActiveTrucks() {
        List<TruckDto> trucks = truckService.getActiveTrucks();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @GetMapping("/{truckId}")
    public ResponseEntity<TruckDto> getTruck(@PathVariable long truckId) {
        TruckDto truck = truckService.getTruck(truckId);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

}