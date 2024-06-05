package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryPlanDto {

    private Long deliveryPlanId;
    private List<RouteDTO> routes;
    private List<DeliveryDTO> deliveries;

    @Override
    public String toString() {
        return "Delivery Plan " + deliveryPlanId +
                "\nRoutes\n" + routes +
                "\nDeliveries\n" + deliveries;
    }
}
