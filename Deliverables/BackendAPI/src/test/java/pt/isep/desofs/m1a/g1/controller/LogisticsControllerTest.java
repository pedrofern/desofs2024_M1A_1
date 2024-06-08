package pt.isep.desofs.m1a.g1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class LogisticsControllerTest {

    @Mock
    private LogisticsService logisticsService;

    @InjectMocks
    private LogisticsController logisticsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubmitForm() {

        SubmitLogisticsForm form = new SubmitLogisticsForm("p1",1L,2L,"01:00", "01:00", 1,2,3);
        PackagingDto packaging= new PackagingDto("p1",1L,2L,"01:00", "01:00", 1,2,3);
        when(logisticsService.submitForm(form)).thenReturn(packaging);

        ResponseEntity<PackagingDto> response = logisticsController.submitForm(form, Mockito.mock(Authentication.class));

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(packaging, response.getBody());
    }

    @Test
    void testGetAllPackaging() {

        PackagingDto packaging = new PackagingDto("p1", 1L,2L,"01:00", "01:00", 1,2,3);
        List<PackagingDto> p = List.of(packaging);
        when(logisticsService.getAllPackaging(0, 5, "deliveryId", "asc", Map.of())).thenReturn(p);

        ResponseEntity<List<PackagingDto>> response = logisticsController.getPackaging(0, 5, "deliveryId", "asc", Map.of());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(p, response.getBody());
    }

    // Add more tests here for other methods in your LogisticsController
}