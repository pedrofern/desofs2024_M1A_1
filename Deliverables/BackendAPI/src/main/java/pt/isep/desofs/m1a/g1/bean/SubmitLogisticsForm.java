package pt.isep.desofs.m1a.g1.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitLogisticsForm {

    private long packagingId;
    private long deliveryId;
    private long truckId;
    private String loadTime;
    private String unloadTime;
    private int x;
    private int y;
    private int z;
}
