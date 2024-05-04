package pt.isep.desofs.m1a.g1.repository;

import java.util.List;
import java.util.Optional;

import pt.isep.desofs.m1a.g1.model.user.Token;

public interface TokenRepository {

	List<Token> findAllValidTokenByUser(String email);

	Optional<Token> findByToken(String token);
	
	Token save(Token token);
	
	void saveAll(List<Token> token);

}
