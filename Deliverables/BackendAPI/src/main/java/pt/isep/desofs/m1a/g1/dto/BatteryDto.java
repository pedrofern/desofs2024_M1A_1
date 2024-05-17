package pt.isep.desofs.m1a.g1.dto;

import lombok.Data;

@Data
public class BatteryDto {

    private long batteryId;
    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

}