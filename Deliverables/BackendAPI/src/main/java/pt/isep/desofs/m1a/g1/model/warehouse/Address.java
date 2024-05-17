package pt.isep.desofs.m1a.g1.model.warehouse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidAddressException;

@Getter
@EqualsAndHashCode
public class Address {
    private final String streetName;
    private final int doorNumber;
    private final String city;
    private final String country;
    private final String zipCode;

    public Address(String streetName, int doorNumber, String city, String country, String zipCode) {
        if (!isValid(streetName, doorNumber, city, country, zipCode)) {
            throw new InvalidAddressException("Invalid address.");
        }
        this.streetName = streetName;
        this.doorNumber = doorNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    private static boolean isValid(String streetName, int doorNumber, String city, String country, String zipCode) {
        return streetName != null && doorNumber >= 0 && city != null && country != null && zipCode != null;
    }
}
