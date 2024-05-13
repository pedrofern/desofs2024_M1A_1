package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class DeliveryJpa {

    @Id
    @GeneratedValue
    private UUID id;

    private Long identifier;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false, length = 64)
    private String warehouseId;
}
