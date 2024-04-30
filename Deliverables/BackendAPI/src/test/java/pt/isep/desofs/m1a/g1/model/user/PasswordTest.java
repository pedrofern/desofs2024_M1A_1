package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

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
		assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
	}

	@Test
	public void testNullPassword() {
		String nullPassword = null;
		assertThrows(IllegalArgumentException.class, () -> new Password(nullPassword));
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
		assertThrows(IllegalArgumentException.class, () -> new Password(passwordExceedsMaxLength));
	}

	@Test
	public void testGetPasswordTwice() {
		String validPassword = "Test@1234";
		Password password = new Password(validPassword);
		assertEquals(validPassword, password.getValue());
		assertThrows(IllegalStateException.class, () -> password.getValue());
	}

}
