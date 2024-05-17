package pt.isep.desofs.m1a.g1.model.truck;

import lombok.Getter;

@Getter
public final class Truck {

    private long truckId;
    private double tare;
    private double loadCapacity;
    private boolean active;
    private Battery battery;

    public Truck(long truckId, double tare, double loadCapacity, boolean active, Battery battery) {
        this.truckId = truckId;
        this.tare = tare;
        this.loadCapacity = loadCapacity;
        this.active = active;
        this.battery = battery;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}