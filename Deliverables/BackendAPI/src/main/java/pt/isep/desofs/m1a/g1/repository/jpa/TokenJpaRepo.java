package pt.isep.desofs.m1a.g1.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pt.isep.desofs.m1a.g1.repository.jpa.model.TokenJpa;

public interface TokenJpaRepo extends JpaRepository<TokenJpa, UUID> {

	@Query(value = """
			select t from TokenJpa t inner join UserJpa u\s
			on t.user.id = u.id\s
			where u.email = :email and (t.expired = false or t.revoked = false)\s
			""")
	List<TokenJpa> findAllValidTokenByUser(String email);

	Optional<TokenJpa> findByToken(String token);

}
