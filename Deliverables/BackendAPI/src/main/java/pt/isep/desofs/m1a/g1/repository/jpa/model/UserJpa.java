package pt.isep.desofs.m1a.g1.repository.jpa.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import pt.isep.desofs.m1a.g1.model.user.Role;

@Data
@Entity
public class UserJpa {
	@Id
	@GeneratedValue
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<TokenJpa> tokens;

	public UserJpa() {
		this.id = UUID.randomUUID();
	}
}
