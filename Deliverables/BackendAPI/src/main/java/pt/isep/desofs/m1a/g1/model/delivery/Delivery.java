package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Delivery {

    private UUID id;
    private Long identifier;
    private LocalDate deliveryDate;
    private Double weight;
    private String warehouseId;
}
