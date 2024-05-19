package pt.isep.desofs.m1a.g1.model.truck;

import org.junit.jupiter.api.Test;
import pt.isep.desofs.m1a.g1.exception.InvalidTruckException;

import static org.junit.jupiter.api.Assertions.*;

public class TruckTest {

    @Test
    public void testConstructor() {
        long truckId = 123;
        double tare = 1000.0;
        double loadCapacity = 2000.0;
        boolean active = true;
        Battery battery = new Battery(321, 100.0, 100.0, 100);

        Truck truck = new Truck(truckId, tare, loadCapacity, active, battery);

        assertEquals(truckId, truck.getTruckId());
        assertEquals(tare, truck.getTare());
        assertEquals(loadCapacity, truck.getLoadCapacity());
        assertEquals(active, truck.isActive());
        assertEquals(battery, truck.getBattery());
    }

    @Test
    public void testConstructorWithInvalidTruckId() {
        assertThrows(InvalidTruckException.class, () -> {
            new Truck(0, 1000.0, 2000.0, true, new Battery(321, 100.0, 100.0, 100));
        });
    }

    @Test
    public void testConstructorWithInvalidTare() {
        assertThrows(InvalidTruckException.class, () -> {
            new Truck(123, -1000.0, 2000.0, true, new Battery(321, 100.0, 100.0, 100));
        });
    }

    @Test
    public void testConstructorWithInvalidLoadCapacity() {
        assertThrows(InvalidTruckException.class, () -> {
            new Truck(123, 1000.0, -2000.0, true, new Battery(321, 100.0, 100.0, 100));
        });
    }

    @Test
    public void testSetTare() {
        Truck truck = new Truck(123, 1000.0, 2000.0, true, new Battery(321, 100.0, 100.0, 100));
        truck.setTare(1500.0);
        assertEquals(1500.0, truck.getTare());
    }

    @Test
    public void testSetLoadCapacity() {
        Truck truck = new Truck(123, 1000.0, 2000.0, true, new Battery(321, 100.0, 100.0, 100));
        truck.setLoadCapacity(2500.0);
        assertEquals(2500.0, truck.getLoadCapacity());
    }

    @Test
    public void testSetActive() {
        Truck truck = new Truck(123, 1000.0, 2000.0, true, new Battery(321, 100.0, 100.0, 100));
        truck.setActive(false);
        assertFalse(truck.isActive());
    }
}
