package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.repository.TokenRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.TokenJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.UserJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.TokenJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TokenJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

@Repository
@Profile("jpa")
public class TokenJpaRepositoryImpl implements TokenRepository {

	@Autowired
	private TokenJpaRepo repo;

	@Autowired
	private UserJpaRepo userRepo;

	private TokenJpaMapper mapper = TokenJpaMapper.INSTANCE;

	@Override
	public List<Token> findAllValidTokenByUser(String email) {
		return mapper.toDomainModel(repo.findAllValidTokenByUser(email));
	}

	@Override
	public Optional<Token> findByToken(String token) {
		Optional<TokenJpa> opt = repo.findByToken(token);
		if (opt.isPresent()) {
			return Optional.of(mapper.toDomainModel(opt.get()));
		}
		return Optional.empty();
	}

	@Override
	public Token save(Token token) {

		TokenJpa tokenJpa = null;
		boolean isUpdate = false;
		if (token.getId() != null) {
			Optional<TokenJpa> byId = repo.findById(token.getId());
			if (byId.isPresent()) {
				tokenJpa = byId.get();
				mapper.updateTokenJpa(tokenJpa, token);
				isUpdate = true;
			}
		}

		if (!isUpdate) {
			tokenJpa = mapper.toDatabaseEntity(token);
			// need to get the user from the database, because user from model do not have id from database
			UserJpa userJpa = userRepo.findByEmail(token.getUser().getEmail().getValue()).get();
			tokenJpa.setUser(userJpa);			
		}

		return mapper.toDomainModel(repo.save(tokenJpa));
	}

	@Override
	public void saveAll(List<Token> token) {
		token.forEach(t -> save(t));
	}

}
