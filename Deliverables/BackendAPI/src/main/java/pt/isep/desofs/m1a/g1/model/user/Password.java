package pt.isep.desofs.m1a.g1.model.user;

import java.util.regex.Pattern;

import pt.isep.desofs.m1a.g1.exception.InvalidPasswordFormatException;
import pt.isep.desofs.m1a.g1.exception.PasswordAlreadyReadException;

public class Password {
	private static final int MIN_LENGTH = 8;
	private static final int MAX_LENGTH = 20;
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{" + MIN_LENGTH + "," + MAX_LENGTH + "}$");

	private final String value;

	private boolean isRead = false;

	public Password(String value) {
		if (!isValid(value)) {
			throw new InvalidPasswordFormatException("Invalid password format.");
		}
		this.value = value;
	}

	private static boolean isValid(String password) {
		return password != null && PASSWORD_PATTERN.matcher(password).matches();
	}

	public String getValue() {
		if (isRead) {
			throw new PasswordAlreadyReadException("Password already read.");
		}
		this.isRead = true;
		return value;
	}
}
