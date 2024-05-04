package pt.isep.desofs.m1a.g1.model.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

@Getter
public final class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4261322957656549155L;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public String getUsername() {
		return this.email.getValue();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.password.getValue();
	}

}
