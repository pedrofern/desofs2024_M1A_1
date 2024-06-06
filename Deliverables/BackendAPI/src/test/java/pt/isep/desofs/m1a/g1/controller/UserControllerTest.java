package pt.isep.desofs.m1a.g1.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import pt.isep.desofs.m1a.g1.bean.AssignNewRoleRequest;
import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.dto.UserDto;
import pt.isep.desofs.m1a.g1.service.UserService;

public class UserControllerTest {

	@InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhoneNumber("911234567");
        request.setRole("ADMIN");
        
        when(userService.register(request)).thenReturn(true);

        ResponseEntity<Boolean> response = userController.register(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    public void testAssignNewRole() {
        AssignNewRoleRequest request = new AssignNewRoleRequest();
        request.setRole("ADMIN");
        when(userService.assignNewRole("1", request.getRole())).thenReturn(true);

        ResponseEntity<Boolean> response = userController.assignNewRole("1", request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }

    @Test
    public void testGetAllUsers() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        List<UserDto> userDtoList = Arrays.asList(userDto);
        when(userService.getAllUsers()).thenReturn(userDtoList);

        ResponseEntity<List<UserDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDtoList, response.getBody());
    }

    @Test
    public void testGetUserById() {
        String email = "test@example.com";
        UserDto userDto = new UserDto();
        userDto.setEmail(email);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(email);
        when(userService.getUser(email)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }
    
    @Test
    public void testGetUserByIdUnauthorized() {
    	String email = "test@example.com";
        UserDto userDto = new UserDto();
        userDto.setEmail(email);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        String email2 = "test2@example.com";
        when(userDetails.getUsername()).thenReturn(email2);
        when(userService.getUser(email)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUserById(email);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
