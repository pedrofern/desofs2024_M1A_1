package pt.isep.desofs.m1a.g1.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class RoleTest {

    @Test
    public void testSystemAdminRoleAuthorities() {
        Role role = Role.ADMIN;
        List<SimpleGrantedAuthority> authorities = role.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_ADMIN", authorities.get(0).getAuthority());
    }

    @Test
    public void testWarehouseManagerRoleAuthorities() {
        Role role = Role.WAREHOUSE_MANAGER;
        List<SimpleGrantedAuthority> authorities = role.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_WAREHOUSE_MANAGER", authorities.get(0).getAuthority());
    }

    @Test
    public void testFleetManagerRoleAuthorities() {
        Role role = Role.FLEET_MANAGER;
        List<SimpleGrantedAuthority> authorities = role.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_FLEET_MANAGER", authorities.get(0).getAuthority());
    }

    @Test
    public void testLogisticsManagerRoleAuthorities() {
        Role role = Role.LOGISTICS_MANAGER;
        List<SimpleGrantedAuthority> authorities = role.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_LOGISTICS_MANAGER", authorities.get(0).getAuthority());
    }

    @Test
    public void testOperatorRoleAuthorities() {
        Role role = Role.OPERATOR;
        List<SimpleGrantedAuthority> authorities = role.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals("ROLE_OPERATOR", authorities.get(0).getAuthority());
    }
}

