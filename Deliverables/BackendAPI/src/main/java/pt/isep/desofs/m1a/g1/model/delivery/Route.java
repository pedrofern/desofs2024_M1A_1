package pt.isep.desofs.m1a.g1.model.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.isep.desofs.m1a.g1.dto.RouteDTO;

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

    public RouteDTO convertToDTO() {
        return new RouteDTO(routeId, idDepartureWarehouse, idArrivalWarehouse, distance, time, energy, extraTime);
    }
}
