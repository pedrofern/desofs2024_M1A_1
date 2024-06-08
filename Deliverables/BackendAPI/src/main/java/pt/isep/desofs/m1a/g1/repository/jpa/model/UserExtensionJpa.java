package pt.isep.desofs.m1a.g1.repository.jpa.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserExtensionJpa {

	@Id
	@GeneratedValue
	private UUID id;
	private int numRetries;

	@Column(unique = true)
	private String username;

	public UserExtensionJpa() {
		this.id = UUID.randomUUID();
	}

}
