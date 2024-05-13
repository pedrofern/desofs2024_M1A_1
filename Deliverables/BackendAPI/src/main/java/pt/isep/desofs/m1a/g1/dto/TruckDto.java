package pt.isep.desofs.m1a.g1.dto;

public class TruckDto {

    private String truckId;
    private double tare;
    private double loadCapacity;
    private boolean active;
    private BatteryDto battery;

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public double getTare() {
        return tare;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BatteryDto getBattery() {
        return battery;
    }

    public void setBattery(BatteryDto battery) {
        this.battery = battery;
    }
}