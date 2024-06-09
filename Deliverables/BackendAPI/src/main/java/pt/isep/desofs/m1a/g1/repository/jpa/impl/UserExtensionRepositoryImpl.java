package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.warrenstrange.googleauth.ICredentialRepository;

import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.UserExtensionRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.UserExtensionJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.UserExtensionJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserExtensionJpa;

@Repository
@Profile("jpa")
public class UserExtensionRepositoryImpl implements UserExtensionRepository, ICredentialRepository {

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

	@Override
	public String getSecretKey(String userName) {
		Optional<UserExtension> byUsername = findByUsername(userName);
		if(!byUsername.isPresent()) {
            return null;
        }
		return byUsername.get().getSecretKey();
	}

	@Override
	public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
		UserExtensionJpa userToSave = null;
		Optional<UserExtensionJpa> opt = repo.findByUsername(userName);
		if (opt.isPresent()) {
			userToSave = opt.get();
			userToSave.setSecretKey(secretKey);
			repo.save(userToSave);
		} else {
			userToSave = new UserExtensionJpa();
			userToSave.setUsername(userName);
			userToSave.setNumRetries(0);
			userToSave.setSecretKey(secretKey);
			repo.save(userToSave);
		}
	}

}
