package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private Long routeId;
    private Long idDepartureWarehouse;
    private Long idArrivalWarehouse;
    private Double distance;
    private Double time;
    private Double energy;
    private Double extraTime;
    private DeliveryPlan deliveryPlan;

    public Route(Long routeId, Long idDepartureWarehouse, Long idArrivalWarehouse, Double distance, Double time, Double energy, Double extraTime) {
        this.routeId = routeId;
        this.idDepartureWarehouse = idDepartureWarehouse;
        this.idArrivalWarehouse = idArrivalWarehouse;
        this.distance = distance;
        this.time = time;
        this.energy = energy;
        this.extraTime = extraTime;
    }

    private void addToDeliveryPlan(DeliveryPlan deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }
}
