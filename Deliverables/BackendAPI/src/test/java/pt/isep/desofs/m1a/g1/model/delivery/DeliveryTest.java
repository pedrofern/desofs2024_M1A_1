package pt.isep.desofs.m1a.g1.model.delivery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeliveryTest {

    @Test
    void testToString() {
        Delivery delivery = new Delivery(1L, "2024-01-01", 10.0, 1L);
        String expected = "Delivery(deliveryId=1, deliveryDate=2024-01-01, weight=10.0, warehouseId=1)";
        assertEquals(expected, delivery.toString());
    }

    @Test
    void getDeliveryId() {
        Delivery delivery = new Delivery(1L, "2024-01-01", 10.0, 1L);
        assertEquals(1L, delivery.getDeliveryId());
    }

    @Test
    void getDeliveryDate() {
        Delivery delivery = new Delivery(1L, "2024-01-01", 10.0, 1L);
        assertEquals("2024-01-01", delivery.getDeliveryDate());
    }

    @Test
    void getWeight() {
        Delivery delivery = new Delivery(1L, "2024-01-01", 10.0, 1L);
        assertEquals(10.0, delivery.getWeight());
    }

    @Test
    void getWarehouseId() {
        Delivery delivery = new Delivery(1L, "2024-01-01", 10.0, 1L);
        assertEquals(1L, delivery.getWarehouseId());
    }

    @Test
    void setDeliveryId() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(1L);
        assertEquals(1L, delivery.getDeliveryId());
    }

    @Test
    void setDeliveryDate() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryDate("2024-01-01");
        assertEquals("2024-01-01", delivery.getDeliveryDate());
    }

    @Test
    void setWeight() {
        Delivery delivery = new Delivery();
        delivery.setWeight(10.0);
        assertEquals(10.0, delivery.getWeight());
    }

    @Test
    void setWarehouseId() {
        Delivery delivery = new Delivery();
        delivery.setWarehouseId(1L);
        assertEquals(1L, delivery.getWarehouseId());
    }

    @Test
    void testEquals() {
        Delivery delivery1 = new Delivery(1L, "2024-01-01", 10.0, 1L);
        Delivery delivery2 = new Delivery(1L, "2024-01-01", 10.0, 1L);
        assertTrue(delivery1.equals(delivery2) && delivery2.equals(delivery1));
        assertEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testHashCode() {
        Delivery delivery = new Delivery(1L, "2024-01-01", 10.0, 1L);
        int expectedHash = delivery.hashCode();
        assertEquals(expectedHash, delivery.hashCode());
    }
}
