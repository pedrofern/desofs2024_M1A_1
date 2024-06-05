package pt.isep.desofs.m1a.g1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RouteDTO {

    private Long routeId;
    private Long idDepartureWarehouse;
    private Long idArrivalWarehouse;
    private Double distance;
    private Double time;
    private Double energy;
    private Double extraTime;
}
