package pt.isep.desofs.m1a.g1.model.logistics;

import lombok.Getter;
import org.owasp.encoder.Encode;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;


@Getter
public final class Packaging {

    private String packagingId;
    private long deliveryId;
    private long truckId;
    private Time loadTime;
    private Time unloadTime;
    private Localization localization;

    public Packaging(String packagingId, long deliveryId, long truckId, String loadTime, String unloadTime, int x, int y, int z) {
        this.packagingId = packagingId;
        this.deliveryId = deliveryId;
        this.truckId = truckId;
        this.loadTime = new Time(loadTime);
        this.unloadTime = new Time(unloadTime);
        this.localization = new Localization(x,y,z);
    }

    public PackagingDto convertToDTO() {
        return new PackagingDto(packagingId,deliveryId,truckId,loadTime.toString(),unloadTime.toString(), localization.getX(), localization.getY(), localization.getZ());
    }

}
