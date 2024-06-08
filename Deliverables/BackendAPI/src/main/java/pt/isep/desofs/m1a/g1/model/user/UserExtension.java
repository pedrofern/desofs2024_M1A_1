package pt.isep.desofs.m1a.g1.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class UserExtension {

	private int numRetries;
	private String username;
	
	public UserExtension(String username, int numRetries) {
		this.numRetries = numRetries;
		this.username = username;
	}
	
	public void incrementRetries() {
        this.numRetries++;
    }
	
	public void resetRetries() {
		this.numRetries = 0;
	}

}
