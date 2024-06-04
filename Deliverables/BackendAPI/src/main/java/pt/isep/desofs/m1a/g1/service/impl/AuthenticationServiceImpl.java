package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.model.user.TokenType;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.TokenRepository;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.service.AuthenticationService;
import pt.isep.desofs.m1a.g1.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepo;
	private final TokenRepository tokenRepo;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
			var jwtToken = jwtService.generateToken(user);
			var refreshToken = jwtService.generateRefreshToken(user);
			revokeAllUserTokens(user);
			saveUserToken(user, jwtToken);
			return new AuthenticationResponse(jwtToken, refreshToken, user.getEmail().getValue(), user.getRole().getName());
		} catch (AuthenticationException e) {
			throw e;
		}
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false)
				.build();
		tokenRepo.save(token);
	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getEmail().getValue());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepo.saveAll(validUserTokens);
	}

}
