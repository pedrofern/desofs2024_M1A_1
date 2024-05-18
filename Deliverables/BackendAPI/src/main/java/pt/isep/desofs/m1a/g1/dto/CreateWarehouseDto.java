package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateWarehouseDto {

    private long identifier;
    private String designation;
    private double latitude;
    private double longitude;
}
