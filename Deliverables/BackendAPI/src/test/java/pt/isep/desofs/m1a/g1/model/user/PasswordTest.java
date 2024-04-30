package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.isep.desofs.m1a.g1.exception.InvalidPasswordFormatException;
import pt.isep.desofs.m1a.g1.exception.PasswordAlreadyReadException;

public class PasswordTest {

	@Test
	public void testValidPassword() {
		String validPassword = "Test@1234";
		Password password = new Password(validPassword);
		assertEquals(validPassword, password.getValue());
	}

	@Test
	public void testInvalidPassword() {
		String invalidPassword = "invalidpassword";
		assertThrows(InvalidPasswordFormatException.class, () -> new Password(invalidPassword));
	}

	@Test
	public void testNullPassword() {
		String nullPassword = null;
		assertThrows(InvalidPasswordFormatException.class, () -> new Password(nullPassword));
	}

	@Test
	public void testPasswordWithMinLength() {
		String passwordWithMinLength = "Test@123";
		Password password = new Password(passwordWithMinLength);
		assertEquals(passwordWithMinLength, password.getValue());
	}

	@Test
	public void testPasswordWithMaxLength() {
		String passwordWithMaxLength = "Test@1234567890";
		Password password = new Password(passwordWithMaxLength);
		assertEquals(passwordWithMaxLength, password.getValue());
	}

	@Test
	public void testPasswordExceedsMaxLength() {
		String passwordExceedsMaxLength = "Test@12345678901234567890";
		assertThrows(InvalidPasswordFormatException.class, () -> new Password(passwordExceedsMaxLength));
	}

	@Test
	public void testGetPasswordTwice() {
		String validPassword = "Test@1234";
		Password password = new Password(validPassword);
		assertEquals(validPassword, password.getValue());
		assertThrows(PasswordAlreadyReadException.class, () -> password.getValue());
	}

}
