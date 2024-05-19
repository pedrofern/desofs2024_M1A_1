package pt.isep.desofs.m1a.g1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeliveryDTO {

    private Long deliveryId;
    private LocalDate deliveryDate;
    private Double weight;
    private Long warehouseId;
}
