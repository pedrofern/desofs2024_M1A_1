package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class RouteJpa {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private Long routeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_warehouseId")
    private WarehouseJpa departureWarehouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_warehouseId")
    private WarehouseJpa arrivalWarehouse;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Double time;

    @Column(nullable = false)
    private Double energy;

    @Column(nullable = false)
    private Double extraTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliveryPlanId")
    private DeliveryPlanJpa deliveryPlan;
}
