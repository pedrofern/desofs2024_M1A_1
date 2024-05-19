package pt.isep.desofs.m1a.g1.model.truck;

import org.junit.jupiter.api.Test;
import pt.isep.desofs.m1a.g1.exception.InvalidBatteryException;

import static org.junit.jupiter.api.Assertions.*;

public class BatteryTest {

    @Test
    public void testConstructor() {
        long batteryId = 321;
        double maximumBattery = 1000.0;
        double autonomy = 12.0;
        double chargingTime = 100;

        Battery battery = new Battery(batteryId, maximumBattery, autonomy, chargingTime);

        assertEquals(batteryId, battery.getBatteryId());
        assertEquals(maximumBattery, battery.getMaximumBattery());
        assertEquals(autonomy, battery.getAutonomy());
        assertEquals(chargingTime, battery.getChargingTime());
    }

    @Test
    public void testConstructorWithInvalidBatteryId() {
        assertThrows(InvalidBatteryException.class, () -> {
            new Battery(0, 1000.0, 12.0, 100.0);
        });
    }

    @Test
    public void testConstructorWithInvalidMaximumBattery() {
        assertThrows(InvalidBatteryException.class, () -> {
            new Battery(321, -1000.0, 12.0, 100.0);
        });
    }

    @Test
    public void testConstructorWithInvalidAutonomy() {
        assertThrows(InvalidBatteryException.class, () -> {
            new Battery(321, 1000.0, -12.0, 100.0);
        });
    }

    @Test
    public void testConstructorWithInvalidChargingTime() {
        assertThrows(InvalidBatteryException.class, () -> {
            new Battery(321, 1000.0, 12.0, -100.0);
        });
    }

    @Test
    public void testSetChargingTime() {
        Battery battery = new Battery(321, 1000.0, 12.0, 100.0);
        battery.setChargingTime(200.0);
        assertEquals(200.0, battery.getChargingTime());
    }

    @Test
    public void testSetAutonomy() {
        Battery battery = new Battery(321, 1000.0, 12.0, 100.0);
        battery.setAutonomy(24.0);
        assertEquals(24.0, battery.getAutonomy());
    }
}
