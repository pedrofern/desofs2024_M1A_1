package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pt.isep.desofs.m1a.g1.model.user.Role;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.jpa.UserJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.UserJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

public class UserJpaRepositoryImplTest {
	
	private UserJpaMapper mapper = UserJpaMapper.INSTANCE;
	
	@InjectMocks
    private UserJpaRepositoryImpl userJpaRepository;

    @Mock
    private UserJpaRepo userJpaRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByEmail() {
    	String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String password = "Test@1234";
		
        UserJpa userJpa = new UserJpa();
        userJpa.setFirstName(firstName);
        userJpa.setLastName(lastName);
        userJpa.setPhoneNumber(phoneNumber);
        userJpa.setEmail(email);
        userJpa.setPassword(password);
        userJpa.setRole(Role.ADMIN);
        
        when(userJpaRepo.findByEmail(email)).thenReturn(Optional.of(userJpa));

        Optional<User> result = userJpaRepository.findByEmail(email);

        verify(userJpaRepo, times(1)).findByEmail(email);
        assertEquals(email, result.get().getUsername());
    }

    @Test
    public void testSave() {
    	
    	String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String password = "Test@1234";
		String role = "ADMIN";

		User user = new User(firstName, lastName, phoneNumber, email, password, role);
		var userJpa =mapper.toDatabaseEntity(user);
		when(userJpaRepo.findByEmail(email)).thenReturn(Optional.of(userJpa));
        when(userJpaRepo.save(userJpa)).thenReturn(userJpa);

        User result = userJpaRepository.save(user);

        verify(userJpaRepo, times(1)).findByEmail(user.getUsername());
        verify(userJpaRepo, times(1)).save(userJpa);
        assertEquals(email, result.getUsername());
    }

}
