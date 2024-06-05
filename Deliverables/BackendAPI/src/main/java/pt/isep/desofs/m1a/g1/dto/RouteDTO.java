package pt.isep.desofs.m1a.g1.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteDTO {

    private Long routeId;
    private Long idDepartureWarehouse;
    private Long idArrivalWarehouse;
    private Double distance;
    private Double time;
    private Double energy;
    private Double extraTime;

    @Override
    public String toString() {
        return "Route " + routeId +
                "\nDeparture Warehouse: " + idDepartureWarehouse +
                "\nArrival Warehouse: " + idArrivalWarehouse +
                "\nDistance: " + distance +
                "\nTime: " + time +
                "\nEnergy: " + energy +
                "\nExtra Time: " + extraTime;
    }
}
