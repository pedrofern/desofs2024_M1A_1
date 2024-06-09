package pt.isep.desofs.m1a.g1.repository.jpa.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MonitoringJpa {

	@Id
	@GeneratedValue
	private UUID id;
	private String uri;
	private String method;
	private String username;
	private LocalDateTime dateTime;

	public MonitoringJpa() {
		this.id = UUID.randomUUID();
	}

}
