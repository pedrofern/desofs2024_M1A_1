package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

    private Long deliveryId;
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;

    @Override
    public String toString() {
        return "\nDelivery " + deliveryId +
                "\nDelivery Date: " + deliveryDate +
                "\nWeight: " + weight +
                "\nWarehouse: " + warehouseId;
    }
}
