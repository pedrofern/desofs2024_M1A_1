package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

    private UUID id;
    private Long identifier;
    private LocalDate deliveryDate;
    private Double weight;
    private String warehouseId;

    public Delivery(long identifier, LocalDate date, double weight, String warehouseId) {
        this.identifier = identifier;
        this.deliveryDate = date;
        this.weight = weight;
        this.warehouseId = warehouseId;
    }
}
