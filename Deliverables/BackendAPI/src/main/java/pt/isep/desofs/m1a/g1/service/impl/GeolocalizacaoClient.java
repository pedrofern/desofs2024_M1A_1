package pt.isep.desofs.m1a.g1.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pt.isep.desofs.m1a.g1.dto.GeolocalizacaoResponseDTO;

@Component
public class GeolocalizacaoClient {

    private static final Logger logger = LoggerFactory.getLogger(GeolocalizacaoClient.class);

    @Value("${geoapi.portugal.localizacao}")
    private String geoApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public GeolocalizacaoResponseDTO obterGeolocalizacao(double latitude, double longitude) {

        logger.info("obterGeolocalizacao: {}, {}", latitude, longitude);
        try {
            String url = geoApiUrl.replace("{latitude}", String.valueOf(latitude))
                    .replace("{longitude}", String.valueOf(longitude));
            return restTemplate.getForObject(url, GeolocalizacaoResponseDTO.class);
        } catch (RestClientException e) {
            logger.error("Erro no obterGeolocalizacao.", e);
            return null;
        }
    }
}
