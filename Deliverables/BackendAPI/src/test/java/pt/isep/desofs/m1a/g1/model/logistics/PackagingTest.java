package pt.isep.desofs.m1a.g1.model.logistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackagingTest {

    @Test
    void testPackagingCreation() {
        Packaging packaging = new Packaging("1", 2L, 3L, "12:30", "13:30", 1, 2, 3);
        assertEquals("1", packaging.getPackagingId());
        assertEquals(2L, packaging.getDeliveryId());
        assertEquals(3L, packaging.getTruckId());
        assertEquals("12:30", packaging.getLoadTime().getValue());
        assertEquals("13:30", packaging.getUnloadTime().getValue());
        assertEquals(1, packaging.getLocalization().getX());
        assertEquals(2, packaging.getLocalization().getY());
        assertEquals(3, packaging.getLocalization().getZ());
    }

    @Test
    void testSanitizeInputDuringPackagingCreation() {
        String input = "<script>alert('Hello');</script>";
        String expectedOutput = "&amp;lt;&amp;#115cript&amp;gt;alert(&amp;#39;Hello&amp;#39;);&amp;lt;/&amp;#115cript&amp;gt;";
        Packaging packaging = new Packaging(input, 2L, 3L, "12:30", "13:30", 1, 2, 3);
        assertEquals(expectedOutput, packaging.getPackagingId());
    }

}
