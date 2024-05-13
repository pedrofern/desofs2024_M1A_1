package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class BatteryJpa {
    @Id
    @GeneratedValue
    private String id;
    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

}