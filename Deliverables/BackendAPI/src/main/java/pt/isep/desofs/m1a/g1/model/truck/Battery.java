package pt.isep.desofs.m1a.g1.model.truck;

import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidBatteryException;

@Getter
public final class Battery {

    private long batteryId;
    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

    public Battery(long batteryId, double maximumBattery, double autonomy, double chargingTime) {
        if (!isValid(batteryId, maximumBattery, autonomy, chargingTime)) {
            throw new InvalidBatteryException("Invalid battery.");
        }
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

    private static boolean isValid(long batteryId, double maximumBattery, double autonomy, double chargingTime) {
        return batteryId > 0 && maximumBattery > 0 && autonomy > 0 && chargingTime > 0;
    }
}