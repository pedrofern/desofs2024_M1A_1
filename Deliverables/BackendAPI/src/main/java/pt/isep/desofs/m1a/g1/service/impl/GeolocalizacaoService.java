package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.dto.GeolocalizacaoResponseDTO;

@Service
public class GeolocalizacaoService {

    @Autowired
    private GeolocalizacaoClient geolocalizacaoClient;

    public GeolocalizacaoResponseDTO obterLocalizacao(double latitude, double longitude){
        return geolocalizacaoClient.obterGeolocalizacao(latitude, longitude);
    }
}
