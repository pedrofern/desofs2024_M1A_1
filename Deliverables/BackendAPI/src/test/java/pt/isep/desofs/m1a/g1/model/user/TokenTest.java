package pt.isep.desofs.m1a.g1.model.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class TokenTest {

	@Test
	public void testTokenAllArgsConstructor() {
		UUID tokenId = UUID.randomUUID();
		String tokenStr = "token123";
		User user = new User("John", "Doe", "+1234567890", "john@example.com", "Test@1234", "ADMIN");

		Token token = new Token(tokenId, tokenStr, TokenType.BEARER, false, false, user);

		assertThat(token.getId()).isEqualTo(tokenId);
		assertThat(token.getToken()).isEqualTo(tokenStr);
		assertThat(token.getTokenType()).isEqualTo(TokenType.BEARER);
		assertThat(token.isRevoked()).isFalse();
		assertThat(token.isExpired()).isFalse();
		assertThat(token.getUser()).isEqualTo(user);
	}

	@Test
	public void testTokenSettersAndGetters() {
		UUID tokenId = UUID.randomUUID();
		String tokenStr = "token123";
		User user = new User("John", "Doe", "+1234567890", "john@example.com", "Test@1234", "OPERATOR");

		Token token = new Token(tokenId, tokenStr, TokenType.BEARER, false, false, user);

		// Utilize setters to modify the values
		UUID newTokenId = UUID.randomUUID();
		String newTokenStr = "newToken123";
		User newUser = new User("Jane", "Smith", "+0987654321", "jane@example.com", "Test@4321", "OPERATOR");

		token.setId(newTokenId);
		token.setToken(newTokenStr);
		token.setTokenType(TokenType.BEARER);
		token.setRevoked(true);
		token.setExpired(true);
		token.setUser(newUser);

		assertThat(token.getId()).isEqualTo(newTokenId);
		assertThat(token.getToken()).isEqualTo(newTokenStr);
		assertThat(token.getTokenType()).isEqualTo(TokenType.BEARER);
		assertThat(token.isRevoked()).isTrue();
		assertThat(token.isExpired()).isTrue();
		assertThat(token.getUser()).isEqualTo(newUser);
	}

	@Test
	public void testTokenDefaultValues() {
		UUID tokenId = UUID.randomUUID();
		String tokenStr = "token123";
		User user = new User("John", "Doe", "+1234567890", "john@example.com", "Test@1234", "ADMIN");

		Token token = new Token(tokenId, tokenStr, TokenType.BEARER, false, false, user);

		assertThat(token.getTokenType()).isEqualTo(TokenType.BEARER);
		assertThat(token.isRevoked()).isFalse();
		assertThat(token.isExpired()).isFalse();
	}
}
