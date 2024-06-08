
package pt.isep.desofs.m1a.g1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.service.AuthenticationService;

public class AuthenticationControllerTest {

	@InjectMocks
	AuthenticationController authenticationController;

	@Mock
	AuthenticationService authenticationService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLoginSuccess() {
		AuthenticationRequest request = new AuthenticationRequest();
		AuthenticationResponse response = new AuthenticationResponse();
		when(authenticationService.authenticate(request)).thenReturn(response);

		ResponseEntity<?> result = authenticationController.login(request);

		assertEquals(ResponseEntity.ok(response), result);
	}

	@Test
	public void testLoginFailure() {
		AuthenticationRequest request = new AuthenticationRequest();
		when(authenticationService.authenticate(request)).thenThrow(new BadCredentialsException("message"));

		ResponseEntity<?> result = authenticationController.login(request);

		assertEquals(401, result.getStatusCode().value());
	    assertEquals(Collections.singletonMap("message", "message"), result.getBody());
	}
}
