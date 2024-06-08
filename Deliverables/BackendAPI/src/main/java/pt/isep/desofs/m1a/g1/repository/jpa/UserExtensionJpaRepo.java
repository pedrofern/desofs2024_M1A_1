package pt.isep.desofs.m1a.g1.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.isep.desofs.m1a.g1.repository.jpa.model.UserExtensionJpa;

public interface UserExtensionJpaRepo extends JpaRepository<UserExtensionJpa, UUID>{
	
	Optional<UserExtensionJpa> findByUsername(String email);
	
}
