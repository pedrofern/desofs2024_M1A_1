package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    private Long deliveryId;
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;

    public DeliveryDTO convertToDTO() {
        return new DeliveryDTO(deliveryId, deliveryDate, weight, warehouseId);
    }
}
