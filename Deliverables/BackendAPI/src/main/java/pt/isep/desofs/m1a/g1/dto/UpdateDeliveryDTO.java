package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateDeliveryDTO {
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;
}
