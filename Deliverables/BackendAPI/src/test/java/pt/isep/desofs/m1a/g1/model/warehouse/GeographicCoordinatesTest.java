package pt.isep.desofs.m1a.g1.model.warehouse;

import org.junit.jupiter.api.Test;
import pt.isep.desofs.m1a.g1.exception.InvalidLatitudeFormatException;
import pt.isep.desofs.m1a.g1.exception.InvalidLongitudeFormatException;

import static org.junit.jupiter.api.Assertions.*;

class GeographicCoordinatesTest {

    @Test
    void changeLatitudeShouldUpdateLatitude() {
        GeographicCoordinates coordinates = new GeographicCoordinates(0, 0);
        coordinates.changeLatitude(45);
        assertEquals(45, coordinates.getLatitude());
    }

    @Test
    void changeLongitudeShouldUpdateLongitude() {
        GeographicCoordinates coordinates = new GeographicCoordinates(0, 0);
        coordinates.changeLongitude(45);
        assertEquals(45, coordinates.getLongitude());
    }

    @Test
    void constructorShouldSetLatitudeAndLongitude() {
        GeographicCoordinates coordinates = new GeographicCoordinates(45, 90);
        assertEquals(45, coordinates.getLatitude());
        assertEquals(90, coordinates.getLongitude());
    }

    @Test
    void constructorShouldThrowExceptionWhenLatitudeIsInvalid() {
        assertThrows(InvalidLatitudeFormatException.class, () -> new GeographicCoordinates(100, 0));
    }

    @Test
    void constructorShouldThrowExceptionWhenLongitudeIsInvalid() {
        assertThrows(InvalidLongitudeFormatException.class, () -> new GeographicCoordinates(0, 200));
    }
}
