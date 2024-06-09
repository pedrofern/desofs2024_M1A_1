package pt.isep.desofs.m1a.g1.model.warehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;

    private static Warehouse getWarehouse() {
        return new Warehouse(
                1L,
                "Warehouse Test",
                "Rua Fernando Maurício",
                "30",
                "Lisboa",
                "Portugal",
                "1950-449",
                38.748406,
                -9.102984,
                true
        );
    }

    @BeforeEach
    void setUp() {
        warehouse = getWarehouse();
    }

    @Test
    void shouldReturnCorrectIdentifier() {
        assertEquals(1L, warehouse.getIdentifier());
    }

    @Test
    void shouldReturnCorrectDesignation() {
        assertEquals("Warehouse Test", warehouse.getDesignation());
    }

    @Test
    void shouldReturnCorrectAddress() {
        Address expectedAddress = new Address("Rua Fernando Maurício", "30", "Lisboa", "Portugal", "1950-449");
        assertEquals(expectedAddress, warehouse.getAddress());
    }

    @Test
    void shouldReturnCorrectGeographicCoordinates() {
        GeographicCoordinates expectedCoordinates = new GeographicCoordinates(38.748406, -9.102984);
        assertEquals(expectedCoordinates, warehouse.getGeographicCoordinates());
    }

    @Test
    void shouldReturnCorrectActiveStatus() {
        assertTrue(warehouse.isActive());
    }

    @Test
    void shouldChangeDesignation() {
        warehouse.changeDesignation("New Designation");
        assertEquals("New Designation", warehouse.getDesignation());
    }

    @Test
    void shouldChangeActiveStatus() {
        warehouse.changeActive(false);
        assertFalse(warehouse.isActive());
    }
}
