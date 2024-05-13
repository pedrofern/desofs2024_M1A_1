package pt.isep.desofs.m1a.g1.model.logistics;

import org.junit.jupiter.api.Test;
import pt.isep.desofs.m1a.g1.exception.InvalidTimeFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeTest {

    @Test
    void testValidTime() {
        Time time = new Time("12:30");
        assertEquals("12:30", time.getValue());
    }

    @Test
    void testInvalidTimeFormat() {
        assertThrows(InvalidTimeFormatException.class, () -> new Time("1230"));
    }

    @Test
    void testInvalidTimeHour() {
        assertThrows(InvalidTimeFormatException.class, () -> new Time("24:00"));
    }

    @Test
    void testInvalidTimeMinute() {
        assertThrows(InvalidTimeFormatException.class, () -> new Time("12:60"));
    }

    @Test
    void testNullTime() {
        assertThrows(InvalidTimeFormatException.class, () -> new Time(null));
    }

}
