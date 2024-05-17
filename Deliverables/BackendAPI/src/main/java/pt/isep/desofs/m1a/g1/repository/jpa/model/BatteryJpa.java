package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class BatteryJpa {
    @Id
    @GeneratedValue
    private UUID id;
    private long batteryId;
    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

}