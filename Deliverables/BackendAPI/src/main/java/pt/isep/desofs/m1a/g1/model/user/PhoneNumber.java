package pt.isep.desofs.m1a.g1.model.user;

import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import pt.isep.desofs.m1a.g1.exception.InvalidPhoneNumberFormatException;

@Getter
@EqualsAndHashCode
public class PhoneNumber {
	private static final int MIN_LENGTH = 8;
	private static final int MAX_LENGTH = 20;
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern
			.compile("^\\+?[0-9]{" + MIN_LENGTH + "," + MAX_LENGTH + "}$");

	private final String value;

	public PhoneNumber(String value) {
		if (!isValid(value)) {
			throw new InvalidPhoneNumberFormatException("Invalid phone number format.");
		}
		this.value = value;
	}

	private static boolean isValid(String phoneNumber) {
		return phoneNumber != null && PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
	}
}
