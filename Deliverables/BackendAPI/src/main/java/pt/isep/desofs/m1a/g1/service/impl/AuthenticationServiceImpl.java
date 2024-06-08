package pt.isep.desofs.m1a.g1.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.model.user.TokenType;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.TokenRepository;
import pt.isep.desofs.m1a.g1.repository.UserExtensionRepository;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.service.AuthenticationService;
import pt.isep.desofs.m1a.g1.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	@Value("${user.number.retry:5}")
	private int numRetries;
	
	private final UserRepository userRepo;
	private final UserExtensionRepository userExtensionRepo;
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
			updateNumberOfRetries(request.getEmail(), false);
			return new AuthenticationResponse(jwtToken, refreshToken);
		} catch (LockedException e) {
			// if is locked, we do not need to increment the number of retries
			throw e;
		} catch (AuthenticationException e) {
			updateNumberOfRetries(request.getEmail(), true);
			throw e;
		}
	}

	private void updateNumberOfRetries(String email, boolean isAuthenticationException) {
		Optional<UserExtension> userExtension = userExtensionRepo.findByUsername(email);
		if (userExtension.isPresent()) {
			UserExtension userExtensionToSave = userExtension.get();
			if (isAuthenticationException) {
				userExtensionToSave.incrementRetries();			
			} else {
				userExtensionToSave.resetRetries();
			}
			userExtensionRepo.save(userExtensionToSave);
			if(userExtensionToSave.getNumRetries() >= numRetries) {
                lockUser(email);
            }
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
	
	private void lockUser(String email) {
		var user = userRepo.findByEmail(email).orElse(null);
		if (user == null)
			return;
		user.lockAccount();
		userRepo.save(user);
	}

}
