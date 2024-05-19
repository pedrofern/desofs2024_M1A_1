package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    private Delivery delivery;
    private DeliveryDTO deliveryDTO;
    private CreateDeliveryDTO createDeliveryDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        delivery = new Delivery();
        delivery.setDeliveryDate(LocalDate.now());
        delivery.setWeight(10.0);
        delivery.setWarehouseId(1L);

        deliveryDTO = new DeliveryDTO();
        deliveryDTO.setDeliveryDate(LocalDate.now());
        deliveryDTO.setWeight(10.0);
        deliveryDTO.setWarehouseId(1L);

        createDeliveryDTO = new CreateDeliveryDTO(LocalDate.now(), 10.0, 1L);
    }

    @Test
    void findAllDeliveries() {
        when(deliveryRepository.findAll()).thenReturn(List.of(delivery));

        List<DeliveryDTO> result = deliveryService.findAllDeliveries();

        assertEquals(1, result.size());
        verify(deliveryRepository, times(1)).findAll();
    }

    @Test
    void findDeliveryByDeliveryId() {
        when(deliveryRepository.findByDeliveryId(anyLong())).thenReturn(delivery);

        DeliveryDTO result = deliveryService.findDeliveryByDeliveryId(1L);

        assertNotNull(result);
        verify(deliveryRepository, times(1)).findByDeliveryId(anyLong());
    }

    @Test
    void createDelivery() {
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        DeliveryDTO result = deliveryService.createDelivery(createDeliveryDTO);

        assertNotNull(result);
        verify(deliveryRepository, times(1)).save(any(Delivery.class));
    }

    @Test
    void updateDelivery() {
        when(deliveryRepository.findByDeliveryId(anyLong())).thenReturn(delivery);
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        DeliveryDTO result = deliveryService.updateDelivery(1L, deliveryDTO);

        assertNotNull(result);
        verify(deliveryRepository, times(1)).findByDeliveryId(anyLong());
        verify(deliveryRepository, times(1)).save(any(Delivery.class));
    }

    @Test
    void deleteDelivery() {
        when(deliveryRepository.existsByIdentifier(anyLong())).thenReturn(true);

        deliveryService.deleteDelivery(1L);

        verify(deliveryRepository, times(1)).deleteByIdentifier(anyLong());
    }
}
