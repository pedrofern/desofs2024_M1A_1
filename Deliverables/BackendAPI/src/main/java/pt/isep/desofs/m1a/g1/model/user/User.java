package pt.isep.desofs.m1a.g1.model.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@NotNull
@AllArgsConstructor
public final class User {	
	
	private Name firstName;
	private Name lastName;
	private PhoneNumber phoneNumber;
	@NotNull
	private Email email;
	
	private Password password;
	@NotNull
	private Role role;

}
