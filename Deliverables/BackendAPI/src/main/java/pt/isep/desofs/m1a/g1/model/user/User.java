package pt.isep.desofs.m1a.g1.model.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
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
	private boolean locked = false;

	public User(String firstName, String lastName, String phoneNumber, String email, String password, String role, boolean locked) {
		this.firstName = new Name(firstName);
		this.lastName = new Name(lastName);
		this.phoneNumber = new PhoneNumber(phoneNumber);
		this.email = new Email(email);
		this.password = new Password(password);
		this.role = Role.fromName(role);
		this.locked = locked;
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
		return !locked;
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
	
	public void assignNewRole(String role) {
		this.role = Role.fromName(role);
	}
	
	public void lockAccount() {
		locked = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		User user = (User) obj;
		return user.email.equals(this.email);
	}
	
	@Override
	public int hashCode() {
		return email.hashCode();
	}

}
