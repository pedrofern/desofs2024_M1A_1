package pt.isep.desofs.m1a.g1.model.truck;

import lombok.Getter;

@Getter
public final class Battery {

    private long batteryId;
    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

    public Battery(long batteryId, double maximumBattery, double autonomy, double chargingTime) {
        this.batteryId = batteryId;
        this.maximumBattery = maximumBattery;
        this.autonomy = autonomy;
        this.chargingTime = chargingTime;
    }

    public void setChargingTime(double chargingTime) {
        this.chargingTime = chargingTime;
    }

    public void setAutonomy(double autonomy) {
        this.autonomy = autonomy;
    }

}