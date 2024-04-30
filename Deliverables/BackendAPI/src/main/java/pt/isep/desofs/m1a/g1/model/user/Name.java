package pt.isep.desofs.m1a.g1.model.user;

import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Name {
	private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

	private final String value;

	public Name(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("Invalid name format.");
		}
		this.value = value;
	}

	private static boolean isValid(String name) {
		return name != null && NAME_PATTERN.matcher(name).matches();
	}
}