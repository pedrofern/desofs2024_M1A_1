package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pt.isep.desofs.m1a.g1.dto.GeolocalizacaoResponseDTO;

public class GeolocalizacaoClientTest {

	@InjectMocks
	private GeolocalizacaoClient geolocalizacaoClient;
	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(geolocalizacaoClient, "geoApiUrl",
				"https://geoapi.pt/gps/{latitude},{longitude}?json=1");
	}

	@Test
	public void testObterGeolocalizacao() {
		double latitude = 40.712776;
		double longitude = -74.005974;
		GeolocalizacaoResponseDTO responseDTO = new GeolocalizacaoResponseDTO();
		responseDTO.setLat(latitude);
		responseDTO.setLon(longitude);

		when(restTemplate.getForObject("https://geoapi.pt/gps/40.712776,-74.005974?json=1",
				GeolocalizacaoResponseDTO.class)).thenReturn(responseDTO);

		GeolocalizacaoResponseDTO result = geolocalizacaoClient.obterGeolocalizacao(latitude, longitude);

		assertTrue(responseDTO.getLat() == result.getLat());
		assertTrue(responseDTO.getLon() == result.getLon());
	}

	@Test
	public void testObterGeolocalizacaoWithException() {
		double latitude = 40.712776;
		double longitude = -74.005974;

		when(restTemplate.getForObject("https://geoapi.pt/gps/40.712776,-74.005974?json=1",
				GeolocalizacaoResponseDTO.class)).thenThrow(new RestClientException("Exception"));

		GeolocalizacaoResponseDTO result = geolocalizacaoClient.obterGeolocalizacao(latitude, longitude);

		assertNull(result);
	}
}
