package pt.isep.desofs.m1a.g1.model.user;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.exception.InvalidRoleFormatException;

@RequiredArgsConstructor
public enum Role {

	ADMIN("ADMIN"), WAREHOUSE_MANAGER("WAREHOUSE_MANAGER"), FLEET_MANAGER("FLEET_MANAGER"),
	LOGISTICS_MANAGER("LOGISTICS_MANAGER"), OPERATOR("OPERATOR");

	public List<SimpleGrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
	}

	private final String name;

	public String getName() {
		return name;
	}

	public static Role fromName(String name) {
		for (Role role : Role.values()) {
			if (role.getName().equals(name)) {
				return role;
			}
		}
		throw new InvalidRoleFormatException("Invalid role name: " + name);
	}

}
