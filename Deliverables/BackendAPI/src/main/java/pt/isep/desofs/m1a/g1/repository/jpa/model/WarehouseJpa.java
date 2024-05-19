package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class WarehouseJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private Long identifier;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String doorNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private boolean active;

    public WarehouseJpa() {
        this.id = UUID.randomUUID();
    }
}
