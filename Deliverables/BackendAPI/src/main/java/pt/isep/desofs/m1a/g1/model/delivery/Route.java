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

    @Override
    public String toString() {
        return "\nrouteId: " + routeId +
                "\nidDepartureWarehouse:" + idDepartureWarehouse +
                "\nidArrivalWarehouse:" + idArrivalWarehouse +
                "\ndistance:" + distance +
                "\ntime:" + time +
                "\nenergy:" + energy +
                "\nextraTime:" + extraTime;
    }
}
