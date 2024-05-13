package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class TruckJpa {
    @Id
    @GeneratedValue
    private String truckId;
    private double tare;
    private double loadCapacity;
    private boolean active;

    @OneToOne
    private BatteryJpa battery;

}