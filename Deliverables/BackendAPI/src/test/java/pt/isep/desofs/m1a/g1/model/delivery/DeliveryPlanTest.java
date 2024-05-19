package pt.isep.desofs.m1a.g1.model.delivery;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPlanTest {

    @Test
    void getRoutes() {
        DeliveryPlan deliveryPlan = new DeliveryPlan();
        Route route = new Route();
        deliveryPlan.setRoutes(List.of(route));
        assertEquals(List.of(route), deliveryPlan.getRoutes());
    }

    @Test
    void getDeliveries() {
        DeliveryPlan deliveryPlan = new DeliveryPlan();
        Delivery delivery = new Delivery();
        deliveryPlan.setDeliveries(List.of(delivery));
        assertEquals(List.of(delivery), deliveryPlan.getDeliveries());
    }

    @Test
    void setRoutes() {
        DeliveryPlan deliveryPlan = new DeliveryPlan();
        Route route = new Route();
        deliveryPlan.setRoutes(List.of(route));
        assertEquals(List.of(route), deliveryPlan.getRoutes());
    }

    @Test
    void setDeliveries() {
        DeliveryPlan deliveryPlan = new DeliveryPlan();
        Delivery delivery = new Delivery();
        deliveryPlan.setDeliveries(List.of(delivery));
        assertEquals(List.of(delivery), deliveryPlan.getDeliveries());
    }

    @Test
    void testEquals() {
        DeliveryPlan deliveryPlan1 = new DeliveryPlan();
        DeliveryPlan deliveryPlan2 = new DeliveryPlan();
        assertTrue(deliveryPlan1.equals(deliveryPlan2) && deliveryPlan2.equals(deliveryPlan1));
        assertEquals(deliveryPlan1.hashCode(), deliveryPlan2.hashCode());
    }

    @Test
    void testHashCode() {
        DeliveryPlan deliveryPlan = new DeliveryPlan();
        int expectedHash = deliveryPlan.hashCode();
        assertEquals(expectedHash, deliveryPlan.hashCode());
    }

    @Test
    void testToString() {
        DeliveryPlan deliveryPlan = new DeliveryPlan();
        assertNotNull(deliveryPlan.toString());
    }
}
