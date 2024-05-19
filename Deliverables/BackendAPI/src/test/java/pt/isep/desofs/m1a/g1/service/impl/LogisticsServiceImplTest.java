package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LogisticsServiceImplTest {

    @Mock
    private PackagingRepository packagingRepo;

    @Mock
    private DeliveryRepository deliveryRepo;

    @InjectMocks
    private LogisticsServiceImpl logisticsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitForm() {
        SubmitLogisticsForm form = new SubmitLogisticsForm();
        form.setPackagingId("1");
        form.setDeliveryId(2L);
        form.setTruckId(3L);
        form.setLoadTime("12:30");
        form.setUnloadTime("13:30");
        form.setX(1);
        form.setY(2);
        form.setZ(3);

        when(packagingRepo.findByPackagingId(any())).thenReturn(Optional.empty());
        when(deliveryRepo.findByDeliveryId(any())).thenReturn(new Delivery());
        when(packagingRepo.findByDelivery(any())).thenReturn(Arrays.asList());

        logisticsService.submitForm(form);

        verify(packagingRepo).save(any());

        Packaging existingPackage = new Packaging("1", 2L, 3L, "12:30", "13:30", 1, 2, 3);
        when(packagingRepo.findByPackagingId(any())).thenReturn(Optional.of(existingPackage));
        assertThrows(IllegalArgumentException.class, () -> logisticsService.submitForm(form));

        existingPackage = new Packaging("1", 2L, 3L, "12:30", "13:30", 1, 2, 3);
        when(packagingRepo.findByDelivery(any())).thenReturn(Arrays.asList(existingPackage));
        assertThrows(IllegalArgumentException.class, () -> logisticsService.submitForm(form));
    }

    @Test
    public void testGetAllPackaging() {
        Packaging packaging1 = new Packaging("1", 2L, 3L, "12:30", "13:30", 1, 2, 3);
        Packaging packaging2 = new Packaging("2", 3L, 4L, "13:30", "14:30", 2, 3, 4);
        List<Packaging> expectedPackagings = Arrays.asList(packaging1, packaging2);

        when(packagingRepo.findAll()).thenReturn(expectedPackagings);

        List<Packaging> result = logisticsService.getAllPackaging();
        assertEquals(expectedPackagings, result);
    }

}
