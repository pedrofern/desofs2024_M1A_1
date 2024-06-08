package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliveryDTO {
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;

    public Delivery convertToEntity() {
        return new Delivery(null, deliveryDate, weight, warehouseId);
    }
}
