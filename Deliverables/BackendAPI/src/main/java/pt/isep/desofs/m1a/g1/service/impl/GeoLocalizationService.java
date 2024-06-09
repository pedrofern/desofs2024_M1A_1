package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.GeoLocalizationResponseDTO;

@Service
public class GeoLocalizationService {

    @Autowired
    private GeoLocalizationClient geoLocalizationClient;

    public GeoLocalizationResponseDTO getGeoLocalization(double latitude, double longitude){
        return geoLocalizationClient.getData(latitude, longitude);
    }
}
