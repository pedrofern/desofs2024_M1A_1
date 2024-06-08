package pt.isep.desofs.m1a.g1.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pt.isep.desofs.m1a.g1.dto.BatteryDto;
import pt.isep.desofs.m1a.g1.dto.TruckDto;
import pt.isep.desofs.m1a.g1.service.TruckService;

import java.util.List;
import java.util.Map;

public class TruckControllerTests {

    @Mock
    private TruckService truckService;

    @InjectMocks
    private TruckController truckController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrucks() {
        TruckDto truckDto = createTruckDto();
        List<TruckDto> trucks = List.of(truckDto);
        when(truckService.getAllTrucks()).thenReturn(trucks);

        ResponseEntity<List<TruckDto>> response = truckController.getAllTrucks();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trucks, response.getBody());
    }

    @Test
    void testGetTruckById() {
        TruckDto truckDto = createTruckDto();
        when(truckService.getTruck(1L)).thenReturn(truckDto);

        ResponseEntity<TruckDto> response = truckController.getTruck(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(truckDto, response.getBody());
    }

    @Test
    void testCreateTruck() {
        TruckDto truckDto = createTruckDto();
        when(truckService.createTruck(truckDto)).thenReturn(truckDto);

        ResponseEntity<TruckDto> response = truckController.createTruck(truckDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(truckDto, response.getBody());
    }

    @Test
    void testUpdateTruck() {
        TruckDto truckDto = createTruckDto();
        when(truckService.updateTruck(1L, truckDto)).thenReturn(truckDto);

        ResponseEntity<TruckDto> response = truckController.updateTruck(1L, truckDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(truckDto, response.getBody());
    }

    @Test
    void testGetTrucks() {
        TruckDto truckDto = createTruckDto();
        List<TruckDto> trucks = List.of(truckDto);
        when(truckService.getTrucks(0, 5, "truckId", "asc", Map.of())).thenReturn(trucks);

        ResponseEntity<List<TruckDto>> response = truckController.getTrucks(0, 5, "truckId", "asc", Map.of());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trucks, response.getBody());
    }

    @Test
    void testGetActiveTrucks() {
        TruckDto truckDto = createTruckDto();
        List<TruckDto> trucks = List.of(truckDto);
        when(truckService.getActiveTrucks()).thenReturn(trucks);

        ResponseEntity<List<TruckDto>> response = truckController.getActiveTrucks();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trucks, response.getBody());
    }

    @Test
    void testCreateTruckInternalServerError() {
        TruckDto truckDto = createTruckDto();
        when(truckService.createTruck(truckDto)).thenThrow(new RuntimeException());

        ResponseEntity<TruckDto> response = truckController.createTruck(truckDto);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testUpdateTruckInternalServerError() {
        TruckDto truckDto = createTruckDto();
        when(truckService.updateTruck(1L, truckDto)).thenThrow(new RuntimeException());

        ResponseEntity<TruckDto> response = truckController.updateTruck(1L, truckDto);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetTruckInternalServerError() {
        when(truckService.getTruck(1L)).thenThrow(new RuntimeException());

        ResponseEntity<TruckDto> response = truckController.getTruck(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetTrucksInternalServerError() {
        when(truckService.getTrucks(0, 5, "truckId", "asc", Map.of())).thenThrow(new RuntimeException());

        ResponseEntity<List<TruckDto>> response = truckController.getTrucks(0, 5, "truckId", "asc", Map.of());

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetAllTrucksInternalServerError() {
        when(truckService.getAllTrucks()).thenThrow(new RuntimeException());

        ResponseEntity<List<TruckDto>> response = truckController.getAllTrucks();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetActiveTrucksInternalServerError() {
        when(truckService.getActiveTrucks()).thenThrow(new RuntimeException());

        ResponseEntity<List<TruckDto>> response = truckController.getActiveTrucks();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    private TruckDto createTruckDto() {
        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(1L);
        truckDto.setTare(5000.0);
        truckDto.setLoadCapacity(20000.0);
        truckDto.setActive(true);
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setBatteryId(1L);
        batteryDto.setMaximumBattery(500.0);
        batteryDto.setAutonomy(1000.0);
        batteryDto.setChargingTime(2.0);
        truckDto.setBattery(batteryDto);
        return truckDto;
    }
}
