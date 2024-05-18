package pt.isep.desofs.m1a.g1.model.warehouse;

import lombok.Getter;
import lombok.Setter;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import java.util.List;

@Getter
public class Warehouse {

    private static final long serialVersionUID = -4261322957656549155L;

    private Long identifier;
    private String designation;
    private Address address;
    private GeographicCoordinates geographicCoordinates;
    private boolean active;
    private List<Delivery> deliveries;

    public Warehouse(Long identifier, String designation, String streetName, String doorNumber, String city, String country, String zipCode, double latitude, double longitude, boolean active, List<Delivery> deliveries) {
        this.identifier = identifier;
        this.designation = designation;
        this.address = new Address(streetName, doorNumber, city, country, zipCode);
        this.geographicCoordinates = new GeographicCoordinates(latitude, longitude);
        this.active = active;
        this.deliveries = deliveries;
    }

    public Long getIdentifier() {
        return this.identifier;
    }

    public String getDesignation() {
        return this.designation;
    }

    public Address getAddress() {
        return this.address;
    }

    public GeographicCoordinates getGeographicCoordinates() {
        return this.geographicCoordinates;
    }

    public boolean isActive() {
        return this.active;
    }

    public List<Delivery> getDeliveries() {
        return this.deliveries;
    }

    public void changeDesignation(String designation) {
        this.designation = designation;
    }

    public void changeActive(boolean active) {
        this.active = active;
    }
}
