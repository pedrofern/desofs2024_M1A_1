package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.repository.jpa.TruckJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.TruckJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

public class TruckJpaRepositoryImplTest {

    private TruckJpaMapper mapper = TruckJpaMapper.INSTANCE;

    @InjectMocks
    private TruckJpaRepositoryImpl truckJpaRepository;

    @Mock
    private TruckJpaRepo truckJpaRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        long truckId = 1L;
        Battery battery = new Battery(1L, 100.0, 500.0, 2.0);
        Truck truck = new Truck(truckId, 5000.0, 10000.0, true, battery);
        TruckJpa truckJpa = mapper.toDatabaseEntity(truck);
        when(truckJpaRepo.findByTruckId(truckId)).thenReturn(Optional.of(truckJpa));

        Optional<Truck> foundTruck = truckJpaRepository.findByTruckId(truckId);

        assertEquals(truckId, foundTruck.get().getTruckId());
        assertEquals(5000.0, foundTruck.get().getTare());
        assertEquals(battery.getBatteryId(), foundTruck.get().getBattery().getBatteryId());
        verify(truckJpaRepo, times(1)).findByTruckId(truckId);
    }

    @Test
    public void testSave() {
        Battery battery = new Battery(1L, 100.0, 500.0, 2.0);
        Truck truck = new Truck(1L, 5000.0, 10000.0, true, battery);
        TruckJpa truckJpa = mapper.toDatabaseEntity(truck);

        when(truckJpaRepo.save(truckJpa)).thenReturn(truckJpa);

        Truck savedTruck = truckJpaRepository.save(truck);

        assertEquals(truck.getTruckId(), savedTruck.getTruckId());
        assertEquals(truck.getTare(), savedTruck.getTare());
        assertEquals(truck.getBattery().getBatteryId(), savedTruck.getBattery().getBatteryId());
        verify(truckJpaRepo, times(1)).save(truckJpa);
    }

    @Test
    public void testFindAll() {
        Battery battery1 = new Battery(1L, 100.0, 500.0, 2.0);
        Battery battery2 = new Battery(2L, 200.0, 600.0, 3.0);
        Truck truck1 = new Truck(1L, 5000.0, 10000.0, true, battery1);
        Truck truck2 = new Truck(2L, 6000.0, 11000.0, true, battery2);
        TruckJpa truckJpa1 = mapper.toDatabaseEntity(truck1);
        TruckJpa truckJpa2 = mapper.toDatabaseEntity(truck2);

        when(truckJpaRepo.findAll()).thenReturn(List.of(truckJpa1, truckJpa2));

        List<Truck> trucks = truckJpaRepository.findAll();

        assertEquals(2, trucks.size());
        verify(truckJpaRepo, times(1)).findAll();
    }

}