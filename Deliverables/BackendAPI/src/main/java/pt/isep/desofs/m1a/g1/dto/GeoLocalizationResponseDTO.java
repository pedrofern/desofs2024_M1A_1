package pt.isep.desofs.m1a.g1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class GeoLocalizationResponseDTO {

    @JsonProperty("lon")
    private double lon;

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("distrito")
    private String distrito;

    @JsonProperty("concelho")
    private String concelho;

    @JsonProperty("freguesia")
    private String freguesia;

    @JsonProperty("altitude_m")
    private int altitudem;

    @JsonProperty("uso")
    private String uso;

    @JsonProperty("SEC")
    private String sec;

    @JsonProperty("SS")
    private String ss;

    @JsonProperty("rua")
    private String rua;

    @JsonProperty("n_porta")
    private String nporta;

    @JsonProperty("CP")
    private String cp;
}
