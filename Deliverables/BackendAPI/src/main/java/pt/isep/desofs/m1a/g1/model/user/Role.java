package pt.isep.desofs.m1a.g1.model.user;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

	SYSTEM_ADMIN, WAREHOUSE_MANAGER, FLEET_MANAGER, LOGISTICS_MANAGER, OPERATOR;

	public List<SimpleGrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
	}

}
