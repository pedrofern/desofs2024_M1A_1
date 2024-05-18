package pt.isep.desofs.m1a.g1.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class GeolocalizacaoResponseDTO {

    private double lon;
    private double lat;
    private String distrito;
    private String concelho;
    private String freguesia;
    private String uso;
    private String SEC;
    private String SS;
    private String rua;
    private String n_porta;
    private String CP;
    private int altitude_m;
}
