package pt.isep.desofs.m1a.g1.model.truck;

import lombok.Getter;

@Getter
public final class Battery {

    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

    public Battery(double maximumBattery, double autonomy, double chargingTime) {
        this.maximumBattery = maximumBattery;
        this.autonomy = autonomy;
        this.chargingTime = chargingTime;
    }
}