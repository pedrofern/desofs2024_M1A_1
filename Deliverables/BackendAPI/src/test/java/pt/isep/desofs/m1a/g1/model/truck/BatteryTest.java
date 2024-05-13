package pt.isep.desofs.m1a.g1.model.truck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatteryTest {

    @Test
    public void testConstructor() {
        double maximumBattery = 1000.0;
        double autonomy = 12.0;
        int chargingTime = 100;

        Battery battery = new Battery(maximumBattery, autonomy, chargingTime);

        assertEquals(maximumBattery, battery.getMaximumBattery());
        assertEquals(autonomy, battery.getAutonomy());
        assertEquals(chargingTime, battery.getChargingTime());
    }
}
