package pt.isep.desofs.m1a.g1.dto;

public class BatteryDto {

    private double maximumBattery;
    private double autonomy;
    private double chargingTime;

    public double getMaximumBattery() {
        return maximumBattery;
    }

    public void setMaximumBattery(double maximumBattery) {
        this.maximumBattery = maximumBattery;
    }

    public double getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(double autonomy) {
        this.autonomy = autonomy;
    }

    public double getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(double chargingTime) {
        this.chargingTime = chargingTime;
    }
}