package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.UserExtensionRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.UserExtensionJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.UserExtensionJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserExtensionJpa;

@Repository
@Profile("jpa")
public class UserExtensionRepositoryImpl implements UserExtensionRepository {

	@Autowired
	private UserExtensionJpaRepo repo;

	private UserExtensionJpaMapper mapper = UserExtensionJpaMapper.INSTANCE;

	@Override
	public Optional<UserExtension> findByUsername(String email) {

		Optional<UserExtensionJpa> opt = repo.findByUsername(email);
		if (opt.isPresent()) {
			return Optional.of(mapper.toDomainModel(opt.get()));
		}
		return Optional.empty();
	}

	@Override
	public UserExtension save(UserExtension user) {
		UserExtensionJpa userToSave = null;
		Optional<UserExtensionJpa> opt = repo.findByUsername(user.getUsername());
		if (opt.isPresent()) {
			userToSave = opt.get();
			mapper.updateUserExtensionJpa(userToSave, user);
		} else {
			userToSave = mapper.toDatabaseEntity(user);
		}
		UserExtensionJpa savedUser = repo.save(userToSave);
		return mapper.toDomainModel(savedUser);
	}

}
