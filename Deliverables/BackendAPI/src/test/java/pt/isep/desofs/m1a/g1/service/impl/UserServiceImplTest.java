package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.model.user.Role;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.validator.PasswordValidator;

public class UserServiceImplTest {

	@Mock
	private UserRepository userRepo;

	@Mock
	private PasswordEncoder passwordEncoder;

	private PasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegister() {
		RegisterRequest request = new RegisterRequest();
		request.setFirstName("John");
		request.setLastName("Doe");
		request.setPhoneNumber("123456789");
		request.setEmail("john.doe@example.com");
		request.setPassword("Pass@1234");
		request.setRole("ADMIN");

		String password = PasswordValidator.createPassword(request.getPassword());
		String passwordEncoded = passwordEncoder2.encode(password);
		User user = new User(request.getFirstName(), request.getLastName(), request.getPhoneNumber(),
				request.getEmail(), passwordEncoded, request.getRole());

		when(userRepo.save(any(User.class))).thenReturn(user);
		when(passwordEncoder.encode(password)).thenReturn(passwordEncoded);

		boolean result = userService.register(request);

		assertTrue(result);
		verify(userRepo, times(1)).save(any(User.class));
	}

	@Test
	public void testAssignNewRole() {
		String id = "john.doe@example.com";
		String role = "ADMIN";

		User user = new User("John", "Doe", "123456789", id, "Pass@1234", "ADMIN");

		when(userRepo.findByEmail(id)).thenReturn(Optional.of(user));
		when(userRepo.save(any(User.class))).thenReturn(user);

		boolean result = userService.assignNewRole(id, role);

		assertTrue(result);
		assertEquals(Role.ADMIN, user.getRole());
		verify(userRepo, times(1)).findByEmail(id);
		verify(userRepo, times(1)).save(any(User.class));
	}

}
