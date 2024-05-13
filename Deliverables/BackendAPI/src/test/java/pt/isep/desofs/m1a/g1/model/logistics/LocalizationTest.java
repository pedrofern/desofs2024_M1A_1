package pt.isep.desofs.m1a.g1.model.logistics;

import org.junit.jupiter.api.Test;
import pt.isep.desofs.m1a.g1.exception.InvalidLocalizationFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalizationTest {

    @Test
    void testValidLocalization() {
        Localization localization = new Localization(1, 2, 3);
        assertEquals(1, localization.getX());
        assertEquals(2, localization.getY());
        assertEquals(3, localization.getZ());
    }

    @Test
    void testInvalidLocalizationNegativeX() {
        assertThrows(InvalidLocalizationFormatException.class, () -> new Localization(-1, 2, 3));
    }

    @Test
    void testInvalidLocalizationNegativeY() {
        assertThrows(InvalidLocalizationFormatException.class, () -> new Localization(1, -2, 3));
    }

    @Test
    void testInvalidLocalizationNegativeZ() {
        assertThrows(InvalidLocalizationFormatException.class, () -> new Localization(1, 2, -3));
    }
}
