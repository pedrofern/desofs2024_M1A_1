package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    private Long deliveryId;
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;
    private DeliveryPlan deliveryPlan;

    public Delivery(Long deliveryId, String deliveryDate, Double weight, Long warehouseId) {
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.weight = weight;
        this.warehouseId = warehouseId;
    }

    private void addToDeliveryPlan(DeliveryPlan deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }
}
