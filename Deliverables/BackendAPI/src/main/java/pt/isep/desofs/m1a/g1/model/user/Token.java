package pt.isep.desofs.m1a.g1.model.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Token {

	private UUID id;
	public String token;
	public TokenType tokenType = TokenType.BEARER;
	public boolean revoked;
	public boolean expired;
	public User user;

}
