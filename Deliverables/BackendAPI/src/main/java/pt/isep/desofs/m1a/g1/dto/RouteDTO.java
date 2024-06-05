package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
        return "\nRoute " + routeId +
                "\nDepartureWarehouse:" + idDepartureWarehouse +
                "\nArrivalWarehouse:" + idArrivalWarehouse +
                "\nDistance:" + distance +
                "\nTime:" + time +
                "\nEnergy:" + energy +
                "\nExtraTime:" + extraTime;
    }
}
