package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class DeliveryJpa {

    @Id
    @GeneratedValue
    private UUID id;

    private Long deliveryId;

    @Column(nullable = false)
    private String deliveryDate;

    @Column(nullable = false)
    private Double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouseId")
    private WarehouseJpa warehouse;
}
