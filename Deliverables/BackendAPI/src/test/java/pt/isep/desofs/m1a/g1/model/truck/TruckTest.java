package pt.isep.desofs.m1a.g1.model.truck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TruckTest {

    @Test
    public void testConstructor() {
        String truckId = "123";
        double tare = 1000.0;
        double loadCapacity = 2000.0;
        boolean active = true;
        Battery battery = new Battery(100.0, 100.0, 100);

        Truck truck = new Truck(truckId, tare, loadCapacity, active, battery);

        assertEquals(truckId, truck.getTruckId());
        assertEquals(tare, truck.getTare());
        assertEquals(loadCapacity, truck.getLoadCapacity());
        assertEquals(active, truck.isActive());
        assertEquals(battery, truck.getBattery());
    }

}