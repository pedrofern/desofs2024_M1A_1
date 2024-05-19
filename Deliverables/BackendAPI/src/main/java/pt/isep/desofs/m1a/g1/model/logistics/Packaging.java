package pt.isep.desofs.m1a.g1.model.logistics;

import lombok.Getter;
import org.owasp.encoder.Encode;


@Getter
public final class Packaging {

    private String packagingId;
    private long deliveryId;
    private long truckId;
    private Time loadTime;
    private Time unloadTime;
    private Localization localization;

    public Packaging(String packagingId, long deliveryId, long truckId, String loadTime, String unloadTime, int x, int y, int z) {
        this.packagingId = sanitizeInput(packagingId);
        this.deliveryId = deliveryId;
        this.truckId = truckId;
        this.loadTime = new Time(loadTime);
        this.unloadTime = new Time(unloadTime);
        this.localization = new Localization(x,y,z);
    }

    public String sanitizeInput(String input) {

        // Replace characters that may be interpreted as HTML or JavaScript with their HTML entity equivalents
        String reworked = input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#39;")
                .replaceAll("script", "&#115cript");

        return Encode.forHtml(reworked);

    }
}
