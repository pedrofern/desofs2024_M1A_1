package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	public void testUserConstructor() {
		Name firstName = new Name("John");
		Name lastName = new Name("Doe");
		PhoneNumber phoneNumber = new PhoneNumber("+1234567890");
		Email email = new Email("john.doe@example.com");
		Password password = new Password("Test@1234");
		Role role = Role.OPERATOR;

		User user = new User(firstName, lastName, phoneNumber, email, password, role);

		assertEquals(firstName, user.getFirstName());
		assertEquals(lastName, user.getLastName());
		assertEquals(phoneNumber, user.getPhoneNumber());
		assertEquals(email, user.getEmail());
		assertEquals(password, user.getPassword());
		assertEquals(role, user.getRole());
	}
}
