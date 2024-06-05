package pt.isep.desofs.m1a.g1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryDTO {

    private Long deliveryId;
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;

    @Override
    public String toString() {
        return "Delivery " + deliveryId +
                "\nDelivery Date: " + deliveryDate +
                "\nWeight: " + weight +
                "\nWarehouse: " + warehouseId;
    }
}
