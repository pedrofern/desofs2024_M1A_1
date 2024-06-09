package pt.isep.desofs.m1a.g1.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pt.isep.desofs.m1a.g1.dto.GeoLocalizationResponseDTO;

@Component
public class GeoLocalizationClient {

    private static final Logger logger = LoggerFactory.getLogger(GeoLocalizationClient.class);

    @Value("${geoapi.portugal.localizacao}")
    private String geoApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public GeoLocalizationResponseDTO getData(double latitude, double longitude) {
        logger.info("get localization: {}, {}", latitude, longitude);
        try {
            String url = geoApiUrl.replace("{latitude}", String.valueOf(latitude))
                    .replace("{longitude}", String.valueOf(longitude));

            GeoLocalizationResponseDTO response = restTemplate.getForObject(url, GeoLocalizationResponseDTO.class);

            if (validateResponse(response)) {
                return response;
            } else {
                logger.error("Invalid response");
                return null;
            }
        } catch (RestClientException e) {
            logger.error("Error get localization", e);
            return null;
        }
    }

    private boolean validateResponse(GeoLocalizationResponseDTO response) {
        if (response == null) {
            return false;
        }
        if (response.getLat() < -90 || response.getLat() > 90) {
            return false;
        }
        if (response.getLon() < -180 || response.getLon() > 180) {
            return false;
        }
        if (response.getDistrito() == null || response.getDistrito().isEmpty()) {
            return false;
        }
        if (response.getConcelho() == null || response.getConcelho().isEmpty()) {
            return false;
        }
        if (response.getFreguesia() == null || response.getFreguesia().isEmpty()) {
            return false;
        }
        if (response.getAltitudem() < 0) {
            return false;
        }
        if (response.getUso() == null || response.getUso().isEmpty()) {
            return false;
        }
        if (response.getSec() == null || !response.getSec().matches("\\d{3}")) {
            return false;
        }
        if (response.getSs() == null || !response.getSs().matches("\\d{2}")) {
            return false;
        }
        if (response.getRua() == null || response.getRua().isEmpty()) {
            return false;
        }
        if (response.getNporta() == null || response.getNporta().isEmpty()) {
            return false;
        }
        return response.getCp() != null && response.getCp().matches("\\d{4}-\\d{3}");
    }
}

