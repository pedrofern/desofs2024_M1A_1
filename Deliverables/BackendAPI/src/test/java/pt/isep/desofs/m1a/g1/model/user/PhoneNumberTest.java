package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

	@Test
	public void testValidPhoneNumber() {
		String validPhoneNumber = "+1234567890";
		PhoneNumber phoneNumber = new PhoneNumber(validPhoneNumber);
		assertEquals(validPhoneNumber, phoneNumber.getValue());
	}

	@Test
	public void testInvalidPhoneNumber() {
		String invalidPhoneNumber = "invalid";
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(invalidPhoneNumber));
	}

	@Test
	public void testNullPhoneNumber() {
		String nullPhoneNumber = null;
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(nullPhoneNumber));
	}

	@Test
    public void testPhoneNumberWithPlusOnly() {
        String phoneNumberWithPlusOnly = "+";
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(phoneNumberWithPlusOnly));
    }

	@Test
	public void testPhoneNumberWithMaxAllowedLength() {
		String phoneNumberWithMaxAllowedLength = "+12345678901234567890";
		PhoneNumber phoneNumber = new PhoneNumber(phoneNumberWithMaxAllowedLength);
		assertEquals(phoneNumberWithMaxAllowedLength, phoneNumber.getValue());
	}

	@Test
	public void testPhoneNumberExceedsMaxLength() {
		String phoneNumberExceedsMaxLength = "+123456789012345678901";
		assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(phoneNumberExceedsMaxLength));
	}

}
