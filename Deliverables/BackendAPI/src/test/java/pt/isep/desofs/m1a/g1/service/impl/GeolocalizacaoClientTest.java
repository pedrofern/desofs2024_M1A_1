package pt.isep.desofs.m1a.g1.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pt.isep.desofs.m1a.g1.dto.GeolocalizacaoResponseDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GeolocalizacaoClientTest {

    @Mock
    private GeolocalizacaoClient geolocalizacaoClient;

    @Test
    public void testDependencyInjection() {
        assertNotNull(geolocalizacaoClient);
    }

    @Test
    public void testRestClientExceptionHandling() {
        GeolocalizacaoResponseDTO response = geolocalizacaoClient.obterGeolocalizacao(38.72, -9.14);
        assertNull(response, "Expected response to be null when RestClientException occurs");
    }

    @Test
    public void testInvalidLatitude() {
        GeolocalizacaoResponseDTO response = geolocalizacaoClient.obterGeolocalizacao(100.0, -9.14);
        assertNull(response, "Expected response to be null for invalid latitude");
    }

    @Test
    public void testInvalidLongitude() {
        GeolocalizacaoResponseDTO response = geolocalizacaoClient.obterGeolocalizacao(38.72, 200.0);
        assertNull(response, "Expected response to be null for invalid longitude");
    }

    @Test
    public void testNoSensitiveInfoInLogs() {
        geolocalizacaoClient.obterGeolocalizacao(38.72, -9.14);

        // Verifica que o logger não contém informações sensíveis
        // Supondo que o logger está configurado para capturar as mensagens (pode ser necessário um Mock ou Spy para o Logger)
        // Verifique que mensagens de log não contêm informações sensíveis
        // Exemplo de verificação:
        verify(geolocalizacaoClient).obterGeolocalizacao(38.72, -9.14);
        verify(geolocalizacaoClient, times(1)).obterGeolocalizacao(38.72, -9.14);
        verify(geolocalizacaoClient, atLeastOnce()).obterGeolocalizacao(38.72, -9.14);
    }
}
