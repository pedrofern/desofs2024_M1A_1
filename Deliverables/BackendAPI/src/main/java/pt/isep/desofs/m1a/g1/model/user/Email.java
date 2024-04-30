package pt.isep.desofs.m1a.g1.model.user;

import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Email {
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

	private final String value;

	public Email(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("Invalid email format.");
		}
		this.value = value;
	}

	private static boolean isValid(String email) {
		return email != null && EMAIL_PATTERN.matcher(email).matches();
	}
}