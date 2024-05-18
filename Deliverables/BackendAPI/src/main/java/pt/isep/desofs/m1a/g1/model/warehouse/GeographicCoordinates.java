package pt.isep.desofs.m1a.g1.model.warehouse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidLatitudeFormatException;
import pt.isep.desofs.m1a.g1.exception.InvalidLongitudeFormatException;

@Getter
@EqualsAndHashCode
public class GeographicCoordinates {

    private double latitude;
    private double longitude;

    public GeographicCoordinates(double latitude, double longitude) {
        if (!isValidLatitude(latitude)) {
            throw new InvalidLatitudeFormatException("Invalid geographic coordinates.");
        }
        if (!isValidLongitude(longitude)) {
            throw new InvalidLongitudeFormatException("Invalid geographic coordinates.");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void changeLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void changeLongitude(double longitude) {
        this.longitude = longitude;
    }

    private static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    private static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }
}
