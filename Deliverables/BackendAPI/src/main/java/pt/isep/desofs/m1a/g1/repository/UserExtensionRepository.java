package pt.isep.desofs.m1a.g1.repository;

import java.util.Optional;

import pt.isep.desofs.m1a.g1.model.user.UserExtension;

public interface UserExtensionRepository {

	UserExtension save(UserExtension user);

	Optional<UserExtension> findByUsername(String email);

}
