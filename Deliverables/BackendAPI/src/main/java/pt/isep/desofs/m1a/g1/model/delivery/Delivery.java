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

    @Override
    public String toString() {
        return "\ndeliveryId: " + deliveryId +
                "\ndeliveryDate: " + deliveryDate +
                "\nweight: " + weight +
                "\nwarehouseId: " + warehouseId;
    }
}
