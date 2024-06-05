package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DeliveryPlan {

    private Long deliveryPlanId;
    private List<Route> routes;
    private List<Delivery> deliveries;

    public DeliveryPlan (Long deliveryPlanId) {
        this.deliveryPlanId = deliveryPlanId;
    }

    @Override
    public String toString() {
        return "Delivery Plan " + deliveryPlanId +
                "\nRoutes\n" + routes +
                "\nDeliveries\n" + deliveries;
    }
}
