package pt.isep.desofs.m1a.g1.model.user;

import pt.isep.desofs.m1a.g1.exception.InvalidPasswordFormatException;
import pt.isep.desofs.m1a.g1.exception.PasswordAlreadyReadException;

public class Password {

	private final String value;

	private boolean isRead = false;

	public Password(String value) {
		if (value == null || value.isEmpty()) {
			throw new InvalidPasswordFormatException("Password cannot be null or empty.");
		}
		this.value = value;
	}

	public String getValue() {
		if (isRead) {
			throw new PasswordAlreadyReadException("Password already read.");
		}
		this.isRead = true;
		return value;
	}
}
