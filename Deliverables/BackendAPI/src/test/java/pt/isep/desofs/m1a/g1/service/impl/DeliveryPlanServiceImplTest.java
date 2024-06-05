package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.RouteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class DeliveryPlanServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private DeliveryPlanServiceImpl deliveryPlanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDeliveryPlan() {
        when(deliveryRepository.findByDeliveryDateAndWarehouseId(anyString(), anyLong())).thenReturn(List.of());
        when(routeRepository.findByArrivalWarehouseId(anyLong())).thenReturn(List.of());

        DeliveryPlan result = deliveryPlanService.getDeliveryPlan("2024-01-01", 1L);
        assertNotNull(result);
    }
}
