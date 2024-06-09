package pt.isep.desofs.m1a.g1.model.warehouse;

import org.junit.jupiter.api.Test;
import pt.isep.desofs.m1a.g1.exception.InvalidAddressException;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void changeStreetNameShouldUpdateStreetName() {
        Address address = new Address("Old Street", "1", "City", "Country", "12345");
        address.changeStreetName("New Street");
        assertEquals("New Street", address.getStreetName());
    }

    @Test
    void changeDoorNumberShouldUpdateDoorNumber() {
        Address address = new Address("Street", "Old Number", "City", "Country", "12345");
        address.changeDoorNumber("New Number");
        assertEquals("New Number", address.getDoorNumber());
    }

    @Test
    void changeCityShouldUpdateCity() {
        Address address = new Address("Street", "1", "Old City", "Country", "12345");
        address.changeCity("New City");
        assertEquals("New City", address.getCity());
    }

    @Test
    void changeCountryShouldUpdateCountry() {
        Address address = new Address("Street", "1", "City", "Old Country", "12345");
        address.changeCountry("New Country");
        assertEquals("New Country", address.getCountry());
    }

    @Test
    void changeZipCodeShouldUpdateZipCode() {
        Address address = new Address("Street", "1", "City", "Country", "Old Zip");
        address.changeZipCode("New Zip");
        assertEquals("New Zip", address.getZipCode());
    }

    @Test
    void constructorShouldThrowExceptionWhenStreetNameIsNull() {
        assertThrows(InvalidAddressException.class, () -> new Address(null, "1", "City", "Country", "12345"));
    }

    @Test
    void constructorShouldThrowExceptionWhenDoorNumberIsNull() {
        assertThrows(InvalidAddressException.class, () -> new Address("Street", null, "City", "Country", "12345"));
    }

    @Test
    void constructorShouldThrowExceptionWhenCityIsNull() {
        assertThrows(InvalidAddressException.class, () -> new Address("Street", "1", null, "Country", "12345"));
    }

    @Test
    void constructorShouldThrowExceptionWhenCountryIsNull() {
        assertThrows(InvalidAddressException.class, () -> new Address("Street", "1", "City", null, "12345"));
    }
}
