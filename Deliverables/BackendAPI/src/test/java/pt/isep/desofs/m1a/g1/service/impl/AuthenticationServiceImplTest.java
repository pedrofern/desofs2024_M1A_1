
package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;

import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.model.user.TokenType;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.TokenRepository;
import pt.isep.desofs.m1a.g1.repository.UserExtensionRepository;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.service.JwtService;

public class AuthenticationServiceImplTest {

	@InjectMocks
	private AuthenticationServiceImpl authenticationService;

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserExtensionRepository userExtensionRepo;
	@Mock
	private TokenRepository tokenRepository;

	@Mock
	private JwtService jwtService;

	@Mock
	private AuthenticationManager authenticationManager;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAuthenticate() {
		String email = "testEmail@batatas.pt";
		String pass = "Pass@1234";
		AuthenticationRequest request = new AuthenticationRequest(email, pass, "", 0);

		User user = new User("testFirstName", "testLastName", "911234567", email, pass, "ADMIN", false);
		String jwtToken = "testToken";
		String refreshToken = "refreshToken";

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(jwtService.generateToken(user)).thenReturn(jwtToken);
		when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);
		when(tokenRepository.findAllValidTokenByUser(user.getEmail().getValue()))
				.thenReturn(List.of(new Token(UUID.randomUUID(), jwtToken, TokenType.BEARER, false, false, user)));

		AuthenticationResponse response = authenticationService.authenticate(request);

		assertNotNull(response);
	}

	@Test
	public void testAuthenticateAuthenticationException() {
		String email = "testEmail@batatas.pt";
		String pass = "Pass@1234";
		AuthenticationRequest request = new AuthenticationRequest(email, pass, "", 0);

		User user = new User("testFirstName", "testLastName", "911234567", email, pass, "ADMIN", false);
		UserExtension userExtension = new UserExtension(email, 0, null);

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(userExtensionRepo.findByUsername(email)).thenReturn(Optional.of(userExtension));
		when(jwtService.generateToken(user)).thenThrow(new BadCredentialsException("a"));

		assertThrows(AuthenticationException.class, () -> {
			authenticationService.authenticate(request);
		});

		verify(userExtensionRepo, times(1)).save(userExtension);
	}

	@Test
	public void testAuthenticateLockedException() {
		String email = "testEmail@batatas.pt";
		String pass = "Pass@1234";
		AuthenticationRequest request = new AuthenticationRequest(email, pass, "", 0);

		User user = new User("testFirstName", "testLastName", "911234567", email, pass, "ADMIN", false);
		UserExtension userExtension = new UserExtension(email, 5, null); // assuming 5 is the maximum number of retries

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
		when(userExtensionRepo.findByUsername(email)).thenReturn(Optional.of(userExtension));
		when(jwtService.generateToken(user)).thenThrow(new LockedException("a"));

		assertThrows(LockedException.class, () -> {
			authenticationService.authenticate(request);
		});
	}
}
