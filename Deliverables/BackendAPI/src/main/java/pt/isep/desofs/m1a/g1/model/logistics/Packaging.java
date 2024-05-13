package pt.isep.desofs.m1a.g1.model.logistics;

import lombok.Getter;


@Getter
public final class Packaging {

    private long packagingId;
    private long deliveryId;
    private long truckId;
    private Time loadTime;
    private Time unloadTime;
    private Localization localization;

    public Packaging(long packagingId, long deliveryId, long truckId, String loadTime, String unloadTime, int x, int y, int z) {
        this.packagingId = packagingId;
        this.deliveryId = deliveryId;
        this.truckId = truckId;
        this.loadTime = new Time(loadTime);
        this.unloadTime = new Time(unloadTime);
        this.localization = new Localization(x,y,z);
    }
}
