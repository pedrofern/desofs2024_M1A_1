
package pt.isep.desofs.m1a.g1.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.repository.TokenRepository;

public class LogoutServiceImplTest {

	@InjectMocks
	private LogoutServiceImpl logoutService;

	@Mock
	private TokenRepository tokenRepository;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private Authentication authentication;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLogout() {
		String jwt = "token";
		Token token = Token.builder().token(jwt).build();

		when(request.getHeader("Authorization")).thenReturn("Bearer " + jwt);
		when(tokenRepository.findByToken(jwt)).thenReturn(Optional.of(token));

		logoutService.logout(request, response, authentication);

		verify(tokenRepository, times(1)).save(token);
		verify(tokenRepository, times(1)).findByToken(jwt);
	}
}
