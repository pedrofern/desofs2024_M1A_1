package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.UserJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.UserJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

@Repository
@Profile("jpa")
public class UserJpaRepositoryImpl implements UserRepository {

	@Autowired
	private UserJpaRepo repo;

	private UserJpaMapper mapper = UserJpaMapper.INSTANCE;

	@Override
	public Optional<User> findByEmail(String email) {

		Optional<UserJpa> opt = repo.findByEmail(email);
		if (opt.isPresent()) {
			return Optional.of(mapper.toDomainModel(opt.get()));
		}
		return Optional.empty();
	}

	@Override
	public User save(User user) {
		UserJpa savedUser = repo.save(mapper.toDatabaseEntity(user));
		return mapper.toDomainModel(savedUser);
	}

}
