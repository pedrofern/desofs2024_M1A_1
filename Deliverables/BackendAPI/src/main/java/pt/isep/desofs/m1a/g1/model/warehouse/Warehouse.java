package pt.isep.desofs.m1a.g1.model.warehouse;

import lombok.Getter;
import lombok.Setter;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import java.util.List;

@Getter
@Setter
public class Warehouse {

    private static final long serialVersionUID = -4261322957656549155L;

    private Long identifier;
    private String designation;
    private Address address;
    private GeographicCoordinates geographicCoordinates;
    private boolean active;
    private List<DeliveryJpa> deliveries;

    public Warehouse() {
    }
    public Warehouse(Long identifier, String designation, String streetName, int doorNumber, String city, String country, String zipCode, double latitude, double longitude, boolean active) {
        this.identifier = identifier;
        this.designation = designation;
        this.address = new Address(streetName, doorNumber, city, country, zipCode);
        this.geographicCoordinates = new GeographicCoordinates(latitude, longitude);
        this.active = active;
    }

    public Warehouse(Long identifier, String designation, String streetName, int doorNumber, String city, String country, String zipCode, double latitude, double longitude, boolean active, List<DeliveryJpa> deliveries) {
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

    public List<DeliveryJpa> getDeliveries() {
        return this.deliveries;
    }
}
