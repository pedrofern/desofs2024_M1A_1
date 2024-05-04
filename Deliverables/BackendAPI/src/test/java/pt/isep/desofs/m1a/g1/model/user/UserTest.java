package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.isep.desofs.m1a.g1.exception.InvalidEmailFormatException;
import pt.isep.desofs.m1a.g1.exception.InvalidNameFormatException;
import pt.isep.desofs.m1a.g1.exception.InvalidPasswordFormatException;
import pt.isep.desofs.m1a.g1.exception.InvalidPhoneNumberFormatException;
import pt.isep.desofs.m1a.g1.exception.InvalidRoleFormatException;

public class UserTest {

	@Test
	public void testConstructor() {
		// Arrange
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String password = "Test@1234";
		String role = "ADMIN";

		// Act
		User user = new User(firstName, lastName, phoneNumber, email, password, role);

		// Assert
		assertEquals(firstName, user.getFirstName().getValue());
		assertEquals(lastName, user.getLastName().getValue());
		assertEquals(phoneNumber, user.getPhoneNumber().getValue());
		assertEquals(email, user.getEmail().getValue());
		assertEquals(password, user.getPassword());
		assertEquals(Role.ADMIN, user.getRole());
	}

	@Test
	public void testConstructor_InvalidName() {
		// Arrange
		String firstName = "John";
		String lastName = null;
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String password = "Test@1234";
		String role = "OPERATOR";

		// Act and Assert
		assertThrows(InvalidNameFormatException.class,
				() -> new User(firstName, lastName, phoneNumber, email, password, role));
	}

	@Test
	public void testConstructor_InvalidEmail() {
		// Arrange
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String invalidEmail = "john@example"; // Invalid email format
		String password = "Test@1234";
		String role = "FLEET_MANAGER";

		// Act and Assert
		assertThrows(InvalidEmailFormatException.class,
				() -> new User(firstName, lastName, phoneNumber, invalidEmail, password, role));
	}

	@Test
	public void testConstructor_InvalidPhoneNumber() {
		// Arrange
		String firstName = "John";
		String lastName = "Doe";
		String invalidPhoneNumber = "123"; // Invalid phone number format
		String email = "john@example.com";
		String password = "Test@1234";
		String role = "LOGISTICS_MANAGER";

		// Act and Assert
		assertThrows(InvalidPhoneNumberFormatException.class,
				() -> new User(firstName, lastName, invalidPhoneNumber, email, password, role));
	}

	@Test
	public void testConstructor_InvalidPassword() {
		// Arrange
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String invalidPassword = null; // Invalid password format
		String role = "WAREHOUSE_MANAGER";

		// Act and Assert
		assertThrows(InvalidPasswordFormatException.class,
				() -> new User(firstName, lastName, phoneNumber, email, invalidPassword, role));
	}

	@Test
	public void testConstructor_InvalidRole() {
		// Arrange
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String password = "Test@1234";
		String role = "batatas"; // Invalid role

		// Act and Assert
		assertThrows(InvalidRoleFormatException.class,
				() -> new User(firstName, lastName, phoneNumber, email, password, role));
	}
}
