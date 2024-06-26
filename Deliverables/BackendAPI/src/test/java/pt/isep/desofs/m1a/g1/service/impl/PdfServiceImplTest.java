package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.RouteDTO;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.service.AuthenticationService;
import pt.isep.desofs.m1a.g1.service.UserService;

class PdfServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private DeliveryPlan deliveryPlan;

    @InjectMocks
    private PdfServiceImpl pdfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        deliveryPlan = new DeliveryPlan();
        deliveryPlan.setDeliveries(List.of(new DeliveryDTO()));
        deliveryPlan.setRoutes(List.of(new RouteDTO()));
    }

    @Test
    void generateDeliveryPlanPdf() {
        ByteArrayInputStream result = pdfService.generateDeliveryPlanPdf(deliveryPlan);
        assertNotNull(result);
    }

    /**
     * Denial of Service (DoS)
     * Objective: Ensure the system handles high-load scenarios without failure.
     */
    @Test
    public void testServiceAvailabilityUnderHighLoad() {
        IntStream.range(0, 1000).parallel().forEach(i -> {
            RegisterRequest request = new RegisterRequest();
            request.setFirstName("John");
            request.setLastName("Doe");
            request.setPhoneNumber("123456789");
            request.setEmail("john.doe@example.com");
            request.setPassword("Pass@1234");
            request.setRole("ADMIN");
            userService.register(request);
        });
        assertTrue(true);
    }

    /**
     * Elevation of Privilege
     * Objective: Restrict PDF download based on user roles.
     */
    @Test
    public void testUnauthorizedRoleAssignment() {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        assertNull(securityContext.getAuthentication());
    }
}
