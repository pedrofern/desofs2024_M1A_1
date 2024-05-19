package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.dto.BatteryDto;
import pt.isep.desofs.m1a.g1.dto.TruckDto;
import pt.isep.desofs.m1a.g1.exception.InvalidBatteryException;
import pt.isep.desofs.m1a.g1.exception.InvalidTruckException;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TruckServiceImplTest {

    @Mock
    private TruckRepository truckRepository;

    @InjectMocks
    private TruckServiceImpl truckService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTruck() {
        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(1L);
        truckDto.setTare(5000.0);
        truckDto.setLoadCapacity(20000.0);
        truckDto.setActive(true);
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setBatteryId(1L);
        batteryDto.setAutonomy(500.0);
        batteryDto.setMaximumBattery(1000.0);
        batteryDto.setChargingTime(2.0);
        truckDto.setBattery(batteryDto);

        Truck truck = new Truck(1L, 5000.0, 20000.0, true, new Battery(1L, 500.0, 1000.0, 2.0));
        when(truckRepository.save(any(Truck.class))).thenReturn(truck);

        TruckDto result = truckService.createTruck(truckDto);

        assertNotNull(result);
        assertEquals(truckDto.getTruckId(), result.getTruckId());
        verify(truckRepository, times(1)).save(any(Truck.class));
    }

    @Test
    public void testCreateTruckWithNullBattery() {
        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(1L);
        truckDto.setTare(5000.0);
        truckDto.setLoadCapacity(20000.0);
        truckDto.setActive(true);
        truckDto.setBattery(null);

        assertThrows(InvalidBatteryException.class, () -> {
            truckService.createTruck(truckDto);
        });
    }

    @Test
    public void testCreateTruckWithNegativeTare() {
        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(1L);
        truckDto.setTare(-5000.0);
        truckDto.setLoadCapacity(20000.0);
        truckDto.setActive(true);
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setBatteryId(1L);
        batteryDto.setAutonomy(500.0);
        batteryDto.setMaximumBattery(1000.0);
        batteryDto.setChargingTime(2.0);
        truckDto.setBattery(batteryDto);

        assertThrows(InvalidTruckException.class, () -> {
            truckService.createTruck(truckDto);
        });
    }

    @Test
    public void testUpdateTruck() {
        Truck truck = new Truck(1L, 5000.0, 20000.0, true, new Battery(1L, 500.0, 1000.0, 2.0));
        when(truckRepository.findByTruckId(1L)).thenReturn(truck);

        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(1L);
        truckDto.setTare(6000.0);
        truckDto.setLoadCapacity(25000.0);
        truckDto.setActive(false);
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setBatteryId(1L);
        batteryDto.setAutonomy(600.0);
        batteryDto.setMaximumBattery(1200.0);
        batteryDto.setChargingTime(3.0);
        truckDto.setBattery(batteryDto);

        when(truckRepository.save(any(Truck.class))).thenReturn(truck);

        TruckDto result = truckService.updateTruck(1L, truckDto);

        assertNotNull(result);
        assertEquals(truckDto.getTare(), result.getTare());
        assertEquals(truckDto.getLoadCapacity(), result.getLoadCapacity());
        assertEquals(truckDto.isActive(), result.isActive());
        verify(truckRepository, times(1)).findByTruckId(1L);
        verify(truckRepository, times(1)).save(any(Truck.class));
    }

    @Test
    public void testUpdateTruckWithNullBattery() {
        Truck truck = new Truck(1L, 5000.0, 20000.0, true, new Battery(1L, 500.0, 1000.0, 2.0));
        when(truckRepository.findByTruckId(1L)).thenReturn(truck);

        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(1L);
        truckDto.setTare(6000.0);
        truckDto.setLoadCapacity(25000.0);
        truckDto.setActive(false);
        truckDto.setBattery(null);

        assertThrows(InvalidBatteryException.class, () -> {
            truckService.updateTruck(1L, truckDto);
        });
    }

    @Test
    public void testGetAllTrucks() {
        Truck truck1 = new Truck(1L, 5000.0, 20000.0, true, new Battery(1L, 500.0, 1000.0, 2.0));
        Truck truck2 = new Truck(2L, 6000.0, 25000.0, false, new Battery(2L, 600.0, 1200.0, 3.0));
        when(truckRepository.findAll()).thenReturn(Arrays.asList(truck1, truck2));

        List<TruckDto> result = truckService.getAllTrucks();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(truckRepository, times(1)).findAll();
    }

    @Test
    public void testGetActiveTrucks() {
        Truck truck1 = new Truck(1L, 5000.0, 20000.0, true, new Battery(1L, 500.0, 1000.0, 2.0));
        Truck truck2 = new Truck(2L, 6000.0, 25000.0, true, new Battery(2L, 600.0, 1200.0, 3.0));
        when(truckRepository.findAllActive()).thenReturn(Arrays.asList(truck1, truck2));

        List<TruckDto> result = truckService.getActiveTrucks();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(truckRepository, times(1)).findAllActive();
    }

    @Test
    public void testGetTruck() {
        Truck truck = new Truck(1L, 5000.0, 20000.0, true, new Battery(1L, 500.0, 1000.0, 2.0));
        when(truckRepository.findByTruckId(1L)).thenReturn(truck);

        TruckDto result = truckService.getTruck(1L);

        assertNotNull(result);
        assertEquals(1L, result.getTruckId());
        verify(truckRepository, times(1)).findByTruckId(1L);
    }

    @Test
    public void testGetTruckNotFound() {
        when(truckRepository.findByTruckId(1L)).thenReturn(null);

        assertThrows(InvalidTruckException.class, () -> {
            TruckDto result = truckService.getTruck(1L);
        });
    }

}
