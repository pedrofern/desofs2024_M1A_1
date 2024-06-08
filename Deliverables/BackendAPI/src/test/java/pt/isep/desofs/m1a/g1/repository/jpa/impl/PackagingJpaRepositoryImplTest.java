package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.repository.jpa.PackagingJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.PackagingJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PackagingJpaRepositoryImplTest {

    private PackagingJpaMapper mapper = PackagingJpaMapper.INSTANCE;

    @InjectMocks
    private PackagingJpaRepositoryImpl logisticsRepository;

    @Mock
    private PackagingJpaRepo logisticsJpaRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByPackagingId() {
        String packagingId = "1";
        long deliveryId = 1L;
        long truckId = 2L;
        String startTime = "01:00";
        String endTime = "01:00";
        int x = 1;
        int y = 2;
        int z = 3;

        DeliveryJpa deliveryJpa = new DeliveryJpa();
        deliveryJpa.setDeliveryId(deliveryId);

        TruckJpa truckJpa = new TruckJpa();
        truckJpa.setTruckId(truckId);

        PackagingJpa packagingJpa = new PackagingJpa();
        packagingJpa.setPackagingId(packagingId);
        packagingJpa.setDelivery(deliveryJpa);
        packagingJpa.setTruck(truckJpa);
        packagingJpa.setLoadTime(startTime);
        packagingJpa.setUnloadTime(endTime);
        packagingJpa.setX(x);
        packagingJpa.setY(y);
        packagingJpa.setZ(z);

        when(logisticsJpaRepo.findByPackagingId(packagingId)).thenReturn(packagingJpa);

        Optional<Packaging> result = logisticsRepository.findByPackagingId(packagingId);

        verify(logisticsJpaRepo, times(1)).findByPackagingId(packagingId);
        assertEquals(packagingId, result.get().getPackagingId());
    }

    @Test
    public void testFindAll() {
        String packagingId = "1";
        long deliveryId = 1L;
        long truckId = 2L;
        String startTime = "01:00";
        String endTime = "01:00";
        int x = 1;
        int y = 2;
        int z = 3;

        DeliveryJpa deliveryJpa = new DeliveryJpa();
        deliveryJpa.setDeliveryId(deliveryId);

        TruckJpa truckJpa = new TruckJpa();
        truckJpa.setTruckId(truckId);

        PackagingJpa packagingJpa = new PackagingJpa();
        packagingJpa.setPackagingId(packagingId);
        packagingJpa.setDelivery(deliveryJpa);
        packagingJpa.setTruck(truckJpa);
        packagingJpa.setLoadTime(startTime);
        packagingJpa.setUnloadTime(endTime);
        packagingJpa.setX(x);
        packagingJpa.setY(y);
        packagingJpa.setZ(z);


        when(logisticsJpaRepo.findAll()).thenReturn(List.of(packagingJpa));

        List<Packaging> result = logisticsRepository.findAll();

        verify(logisticsJpaRepo, times(1)).findAll();
        assertEquals(packagingId, result.get(0).getPackagingId());
    }
}