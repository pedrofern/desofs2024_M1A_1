package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPlan {

    private List<Route> routes;
    private List<Delivery> deliveries;
}
