package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class DeliveryPlanServiceImplTest {

    @InjectMocks
    private DeliveryPlanServiceImpl deliveryPlanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDeliveryPlanByDeliveryIdAndDeliveryWarehouseId() {
        when(deliveryPlanService.getDeliveryPlan(anyLong())).thenReturn(new DeliveryPlan());

        DeliveryPlan result = deliveryPlanService.getDeliveryPlan(1L);
        assertNotNull(result);
    }
}
