package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.delivery.Route;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PdfServiceImplTest {

    @InjectMocks
    private PdfServiceImpl pdfService;

    private DeliveryPlan deliveryPlan;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        deliveryPlan = new DeliveryPlan();
        deliveryPlan.setDeliveries(List.of(new Delivery()));
        deliveryPlan.setRoutes(List.of(new Route()));
    }

    @Test
    void generateDeliveryPlanPdf() {
        ByteArrayInputStream result = pdfService.generateDeliveryPlanPdf(deliveryPlan);

        assertNotNull(result);
    }
}
