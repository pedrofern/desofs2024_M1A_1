package pt.isep.desofs.m1a.g1.dto;

import lombok.Data;

@Data
public class TruckDto {

    private long truckId;
    private double tare;
    private double loadCapacity;
    private boolean active;
    private BatteryDto battery;

}