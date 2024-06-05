package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class DeliveryPlanJpa {

    @Id
    @GeneratedValue
    private UUID id;

    private Long deliveryPlanId;

    @OneToMany(mappedBy = "deliveryPlan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RouteJpa> routes;

    @OneToMany(mappedBy = "deliveryPlan", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryJpa> deliveries;
}
