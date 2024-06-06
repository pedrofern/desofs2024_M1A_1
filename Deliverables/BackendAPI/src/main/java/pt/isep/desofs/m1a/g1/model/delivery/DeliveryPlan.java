package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.RouteDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPlan {

    private List<RouteDTO> routes;
    private List<DeliveryDTO> deliveries;

    @Override
    public String toString() {
        return "Delivery Plan" +
                "\nRoutes: " + routes +
                "\nDeliveries: " + deliveries;
    }
}
