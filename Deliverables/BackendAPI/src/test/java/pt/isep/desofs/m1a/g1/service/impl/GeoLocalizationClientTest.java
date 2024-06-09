package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pt.isep.desofs.m1a.g1.dto.GeoLocalizationResponseDTO;

public class GeoLocalizationClientTest {

	@InjectMocks
	private GeoLocalizationClient geoLocalizationClient;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(geoLocalizationClient, "geoApiUrl",
				"https://geoapi.pt/gps/{latitude},{longitude}?json=1");
	}

	private static GeoLocalizationResponseDTO getGeoLocalizationResponseDTO() {
		GeoLocalizationResponseDTO responseDTO = new GeoLocalizationResponseDTO();
		responseDTO.setLat(38.748406);
		responseDTO.setLon(-9.102984);
		responseDTO.setDistrito("Lisboa");
		responseDTO.setConcelho("Lisboa");
		responseDTO.setFreguesia("Marvila");
		responseDTO.setAltitudem(36);
		responseDTO.setUso("Tecido edificado contínuo predominantemente vertical");
		responseDTO.setSec("011");
		responseDTO.setSs("06");
		responseDTO.setRua("Rua Fernando Maurício");
		responseDTO.setNporta("30");
		responseDTO.setCp("1950-449");
		return responseDTO;
	}

//	@Test
//	public void testGetData() {
//		String url = "https://geoapi.pt/gps/{latitude},{longitude}?json=1";
//		double latitude = 38.748406;
//		double longitude = -9.102984;
//		GeoLocalizationResponseDTO responseDTO = getGeoLocalizationResponseDTO();
//
//		when(restTemplate.getForObject(url, GeoLocalizationResponseDTO.class)).thenReturn(responseDTO);
//
//		GeoLocalizationResponseDTO result = geoLocalizationClient.getData(latitude, longitude);
//
//		assertNotNull(result);
//		assertTrue(responseDTO.getLat() == result.getLat());
//		assertTrue(responseDTO.getLon() == result.getLon());
//	}

	@Test
	public void testGetDataWithException() {
		double latitude = 40.712776;
		double longitude = -74.005974;

		when(restTemplate.getForObject("https://geoapi.pt/gps/40.712776,-74.005974?json=1",
				GeoLocalizationResponseDTO.class)).thenThrow(new RestClientException("Exception"));

		GeoLocalizationResponseDTO result = geoLocalizationClient.getData(latitude, longitude);

		assertNull(result);
	}
}
