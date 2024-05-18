package pt.isep.desofs.m1a.g1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateWarehouseDto {

    private String designation;
    private String streetName;
    private String doorNumber;
    private String city;
    private String country;
    private String zipCode;
    private double latitude;
    private double longitude;
    private boolean active;
}
