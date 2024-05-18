package pt.isep.desofs.m1a.g1.model.warehouse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidAddressException;

@Getter
@EqualsAndHashCode
public class Address {
    private String streetName;
    private String doorNumber;
    private String city;
    private String country;
    private String zipCode;

    public Address(String streetName, String doorNumber, String city, String country, String zipCode) {
        if (!isValid(streetName, doorNumber, city, country)) {
            throw new InvalidAddressException("Invalid address.");
        }
        this.streetName = streetName;
        this.doorNumber = doorNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public void changeStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void changeDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void changeCity(String city) {
        this.city = city;
    }

    public void changeCountry(String country) {
        this.country = country;
    }

    public void changeZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    private static boolean isValid(String streetName, String doorNumber, String city, String country) {
        return streetName != null && doorNumber != null && city != null && country != null;
    }
}
