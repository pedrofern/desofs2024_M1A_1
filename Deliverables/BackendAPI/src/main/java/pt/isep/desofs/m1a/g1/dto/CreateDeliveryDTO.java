package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateDeliveryDTO {
    private LocalDate deliveryDate;
    private Double weight;
    private Long warehouseId;
}
