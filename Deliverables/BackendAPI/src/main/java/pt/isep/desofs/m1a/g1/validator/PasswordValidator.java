package pt.isep.desofs.m1a.g1.validator;

import java.util.regex.Pattern;

import pt.isep.desofs.m1a.g1.exception.InvalidPasswordFormatException;

public class PasswordValidator {

	private static final int MIN_LENGTH = 8;
	private static final int MAX_LENGTH = 20;
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{" + MIN_LENGTH + "," + MAX_LENGTH + "}$");

	public static String createPassword(String value) {
		if (!isValid(value)) {
			throw new InvalidPasswordFormatException("Invalid password format.");
		}
		return value;
	}

	private static boolean isValid(String password) {
		return password != null && PASSWORD_PATTERN.matcher(password).matches();
	}
}
