package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PackagingDto {

    private String packagingId;
    private long deliveryId;
    private long truckId;
    private String loadTime;
    private String unloadTime;
    private int x;
    private int y;
    private int z;
}
