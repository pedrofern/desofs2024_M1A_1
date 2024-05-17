package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class TruckJpa {
    @Id
    @GeneratedValue
    private UUID id;
    private long truckId;
    private double tare;
    private double loadCapacity;
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    private BatteryJpa battery;

}