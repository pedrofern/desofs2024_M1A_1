package pt.isep.desofs.m1a.g1.model.logistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackagingTest {

    @Test
    void testPackagingCreation() {
        Packaging packaging = new Packaging(1L, 2L, 3L, "12:30", "13:30", 1, 2, 3);
        assertEquals(1L, packaging.getPackagingId());
        assertEquals(2L, packaging.getDeliveryId());
        assertEquals(3L, packaging.getTruckId());
        assertEquals("12:30", packaging.getLoadTime().getValue());
        assertEquals("13:30", packaging.getUnloadTime().getValue());
        assertEquals(1, packaging.getLocalization().getX());
        assertEquals(2, packaging.getLocalization().getY());
        assertEquals(3, packaging.getLocalization().getZ());
    }
}
