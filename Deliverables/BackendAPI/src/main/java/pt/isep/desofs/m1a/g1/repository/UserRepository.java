package pt.isep.desofs.m1a.g1.repository;

import java.util.Optional;

import pt.isep.desofs.m1a.g1.model.user.User;

public interface UserRepository {

	User save(User user);

	Optional<User> findByEmail(String email);

}
