package pt.isep.desofs.m1a.g1.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class UserExtension {

	private int numRetries;
	private String username;
	private String secretKey = null;
	
	public UserExtension(String username, int numRetries, String secretKey) {
		this.numRetries = numRetries;
		this.username = username;
		this.secretKey = secretKey;
	}
	
	public void incrementRetries() {
        this.numRetries++;
    }
	
	public void resetRetries() {
		this.numRetries = 0;
	}

}
