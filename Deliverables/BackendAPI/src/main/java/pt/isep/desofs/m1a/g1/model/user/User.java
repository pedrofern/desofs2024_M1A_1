package pt.isep.desofs.m1a.g1.model.user;

import lombok.Getter;

@Getter
public final class User {

	private Name firstName;
	private Name lastName;
	private PhoneNumber phoneNumber;
	private Email email;
	private Password password;
	private Role role;

	public User(String firstName, String lastName, String phoneNumber, String email, String password, String role) {
		this.firstName = new Name(firstName);
		this.lastName = new Name(lastName);
		this.phoneNumber = new PhoneNumber(phoneNumber);
		this.email = new Email(email);
		this.password = new Password(password);
		this.role = Role.fromName(role);
	}

}
