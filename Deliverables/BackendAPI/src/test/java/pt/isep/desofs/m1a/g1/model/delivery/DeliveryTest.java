package pt.isep.desofs.m1a.g1.model.delivery;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void testToString() {
        Delivery delivery = new Delivery(1L, LocalDate.now(), 10.0, 1L);
        String expected = "\ndeliveryId: " + 1L +
                "\ndeliveryDate: " + LocalDate.now() +
                "\nweight: " + 10.0 +
                "\nwarehouseId: " + 1L;
        assertEquals(expected, delivery.toString());
    }

    @Test
    void getDeliveryId() {
        Delivery delivery = new Delivery(1L, LocalDate.now(), 10.0, 1L);
        assertEquals(1L, delivery.getDeliveryId());
    }

    @Test
    void getDeliveryDate() {
        LocalDate date = LocalDate.now();
        Delivery delivery = new Delivery(1L, date, 10.0, 1L);
        assertEquals(date, delivery.getDeliveryDate());
    }

    @Test
    void getWeight() {
        Delivery delivery = new Delivery(1L, LocalDate.now(), 10.0, 1L);
        assertEquals(10.0, delivery.getWeight());
    }

    @Test
    void getWarehouseId() {
        Delivery delivery = new Delivery(1L, LocalDate.now(), 10.0, 1L);
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
        LocalDate date = LocalDate.now();
        Delivery delivery = new Delivery();
        delivery.setDeliveryDate(date);
        assertEquals(date, delivery.getDeliveryDate());
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
        Delivery delivery1 = new Delivery(1L, LocalDate.now(), 10.0, 1L);
        Delivery delivery2 = new Delivery(1L, LocalDate.now(), 10.0, 1L);
        assertTrue(delivery1.equals(delivery2) && delivery2.equals(delivery1));
        assertEquals(delivery1.hashCode(), delivery2.hashCode());
    }

    @Test
    void testHashCode() {
        Delivery delivery = new Delivery(1L, LocalDate.now(), 10.0, 1L);
        int expectedHash = delivery.hashCode();
        assertEquals(expectedHash, delivery.hashCode());
    }
}
