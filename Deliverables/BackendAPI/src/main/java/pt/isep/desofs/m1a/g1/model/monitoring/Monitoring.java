package pt.isep.desofs.m1a.g1.model.monitoring;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Monitoring {

	private String uri;
	private String method;
	private String username;
	private LocalDateTime dateTime;

}
