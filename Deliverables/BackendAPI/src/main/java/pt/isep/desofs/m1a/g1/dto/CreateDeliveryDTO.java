package pt.isep.desofs.m1a.g1.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDeliveryDTO {
    private String deliveryDate;
    private Double weight;
    private Long warehouseId;
}
