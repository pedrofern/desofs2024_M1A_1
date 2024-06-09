package pt.isep.desofs.m1a.g1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.UpdateDeliveryDTO;
import pt.isep.desofs.m1a.g1.service.PdfService;
import pt.isep.desofs.m1a.g1.service.impl.DeliveryPlanServiceImpl;
import pt.isep.desofs.m1a.g1.service.impl.DeliveryServiceImpl;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DeliveryControllerTest {

    @Mock
    private DeliveryServiceImpl deliveryServiceImpl;

    @Mock
    private DeliveryPlanServiceImpl deliveryPlanService;

    @Mock
    private PdfService pdfService;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDeliveries() {
        DeliveryDTO delivery = new DeliveryDTO(1L, "2023-06-08", 10.0, 1L);
        List<DeliveryDTO> deliveries = List.of(delivery);
        when(deliveryServiceImpl.getAllDeliveries()).thenReturn(deliveries);

        ResponseEntity<List<DeliveryDTO>> response = deliveryController.getAllDeliveries();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deliveries, response.getBody());
    }

    @Test
    void testGetDeliveries() {
        DeliveryDTO delivery = new DeliveryDTO(1L, "2023-06-08", 10.0, 1L);
        List<DeliveryDTO> deliveries = List.of(delivery);
        when(deliveryServiceImpl.getDeliveries(0, 5, "deliveryId", "asc", Map.of())).thenReturn(deliveries);

        ResponseEntity<List<DeliveryDTO>> response = deliveryController.getDeliveries(0, 5, "deliveryId", "asc", Map.of());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deliveries, response.getBody());
    }

    @Test
    void testGetDeliveryById() {
        DeliveryDTO delivery = new DeliveryDTO(1L, "2023-06-08", 10.0, 1L);
        when(deliveryServiceImpl.findDeliveryByDeliveryId(1L)).thenReturn(delivery);

        ResponseEntity<DeliveryDTO> response = deliveryController.getDeliveryById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(delivery, response.getBody());
    }

    @Test
    void testCreateDelivery() {
        CreateDeliveryDTO createDeliveryDTO = new CreateDeliveryDTO("2023-06-08", 10.0, 1L);
        DeliveryDTO delivery = new DeliveryDTO(1L, "2023-06-08", 10.0, 1L);
        when(deliveryServiceImpl.createDelivery(createDeliveryDTO)).thenReturn(delivery);

        ResponseEntity<DeliveryDTO> response = deliveryController.createDelivery(createDeliveryDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(delivery, response.getBody());
    }

    @Test
    void testUpdateDelivery() {
        UpdateDeliveryDTO updateDeliveryDTO = new UpdateDeliveryDTO("2023-06-08", 10.0, 1L);
        DeliveryDTO delivery = new DeliveryDTO(1L, "2023-06-08", 10.0, 1L);
        when(deliveryServiceImpl.updateDelivery(1L, updateDeliveryDTO)).thenReturn(delivery);

        ResponseEntity<DeliveryDTO> response = deliveryController.updateDelivery(1L, updateDeliveryDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(delivery, response.getBody());
    }

    @Test
    void testDeleteDelivery() {
        ResponseEntity<Void> response = deliveryController.deleteDelivery(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    void testGetDeliveryPlanPdf() {
//        try {
//            DeliveryDTO deliveryDTO = new DeliveryDTO(1L, "2023-06-08", 10.0, 1L);
//            RouteDTO routeDTO = new RouteDTO(1L, 1L, 1L, 1.0, 1.0, 1.0, 1.0);
//            DeliveryPlan deliveryPlan = new DeliveryPlan(List.of(routeDTO), List.of(deliveryDTO));
//
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            PdfWriter writer = new PdfWriter(out);
//            PdfDocument pdf = new PdfDocument(writer);
//            Document document = new Document(pdf);
//            document.add(new Paragraph("Delivery Plan"));
//            document.close();
//            ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
//
//            when(pdfService.generateDeliveryPlanPdf(deliveryPlan)).thenReturn(bis);
//
//            ResponseEntity<byte[]> response = deliveryController.getDeliveryPlanPdf("2023-06-08", 1L);
//
//            assertNotNull(response);
//            assertEquals(HttpStatus.OK, response.getStatusCode());
//            assertNotNull(response.getBody());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Exception occurred: " + e.getMessage());
//        }
//    }
}
