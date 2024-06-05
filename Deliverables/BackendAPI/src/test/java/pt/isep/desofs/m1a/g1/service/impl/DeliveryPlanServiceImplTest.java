package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;

class DeliveryPlanServiceImplTest {

	@Mock
	private DeliveryPlanServiceImpl deliveryPlanService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getDeliveryPlanByDeliveryIdAndDeliveryWarehouseId() {
		when(deliveryPlanService.getDeliveryPlan(1L)).thenReturn(new DeliveryPlan());

		DeliveryPlan result = deliveryPlanService.getDeliveryPlan(1L);
		assertNotNull(result);
	}
}
