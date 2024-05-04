package pt.isep.desofs.m1a.g1.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

public interface UserJpaRepo extends JpaRepository<UserJpa, UUID>{
	
	Optional<UserJpa> findByEmail(String email);
	
}
