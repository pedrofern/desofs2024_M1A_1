package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.jpa.UserExtensionJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.UserExtensionJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserExtensionJpa;

public class UserExtensionRepositoryImplTest {

	@InjectMocks
	private UserExtensionRepositoryImpl userExtensionRepository;

	@Mock
	private UserExtensionJpaRepo userExtensionJpaRepo;

	@Mock
	private UserExtensionJpaMapper userExtensionJpaMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindByUsername() {
		String username = "test@example.com";
		UserExtensionJpa userExtensionJpa = new UserExtensionJpa();
		UserExtension userExtension = new UserExtension(username, 0, null);

		when(userExtensionJpaRepo.findByUsername(username)).thenReturn(Optional.of(userExtensionJpa));
		when(userExtensionJpaMapper.toDomainModel(userExtensionJpa)).thenReturn(userExtension);

		Optional<UserExtension> result = userExtensionRepository.findByUsername(username);

		assertTrue(result.isPresent());
		assertEquals(userExtension, result.get());
	}

	@Test
	public void testSave() {
		String username = "test@example.com";
		UserExtension userExtension = new UserExtension(username, 0, null);
		UserExtensionJpa userExtensionJpa = new UserExtensionJpa();

		when(userExtensionJpaRepo.findByUsername(userExtension.getUsername())).thenReturn(Optional.empty());
		when(userExtensionJpaMapper.toDatabaseEntity(userExtension)).thenReturn(userExtensionJpa);
		when(userExtensionJpaRepo.save(userExtensionJpa)).thenReturn(userExtensionJpa);
		when(userExtensionJpaMapper.toDomainModel(userExtensionJpa)).thenReturn(userExtension);

		UserExtension result = userExtensionRepository.save(userExtension);

		assertEquals(userExtension, result);
	}

	@Test
	public void testSaveWhenUserExtensionIsPresent() {
		String username = "test@example.com";
		UserExtension userExtension = new UserExtension(username, 0, null);
		UserExtensionJpa userExtensionJpa = new UserExtensionJpa();

		when(userExtensionJpaRepo.findByUsername(userExtension.getUsername()))
				.thenReturn(Optional.of(userExtensionJpa));
		doAnswer(invocation -> {
			UserExtensionJpa updatedUserExtensionJpaArg = invocation.getArgument(0);
			updatedUserExtensionJpaArg.setUsername(username);
			return null; // return null for void methods
		}).when(userExtensionJpaMapper).updateUserExtensionJpa(userExtensionJpa, userExtension);

		when(userExtensionJpaRepo.save(userExtensionJpa)).thenReturn(userExtensionJpa);
		when(userExtensionJpaMapper.toDomainModel(userExtensionJpa)).thenReturn(userExtension);

		UserExtension result = userExtensionRepository.save(userExtension);

		assertEquals(userExtension, result);

	}
	
	@Test
	public void testGetSecretKey() {
		String username = "test@example.com";
	    String secretKey = "secretKey";
	    UserExtensionJpa userExtensionJpa = new UserExtensionJpa();
	    userExtensionJpa.setSecretKey(secretKey);
	    UserExtension userExtension = new UserExtension(username, 0, secretKey);

	    when(userExtensionJpaRepo.findByUsername(username)).thenReturn(Optional.of(userExtensionJpa));
	    when(userExtensionJpaMapper.toDomainModel(userExtensionJpa)).thenReturn(userExtension);

	    String result = userExtensionRepository.getSecretKey(username);

	    assertEquals(secretKey, result);
	}
	
	@Test
	public void testSaveUserCredentials() {
	    String username = "test@example.com";
	    String secretKey = "secretKey";
	    UserExtensionJpa userExtensionJpa = new UserExtensionJpa();

	    when(userExtensionJpaRepo.findByUsername(username)).thenReturn(Optional.of(userExtensionJpa));

	    userExtensionRepository.saveUserCredentials(username, secretKey, 0, null);

	    verify(userExtensionJpaRepo, times(1)).save(Mockito.any(UserExtensionJpa.class));
	}
	
	@Test
	public void testSaveUserCredentialsEmpty() {
	    String username = "test@example.com";
	    String secretKey = "secretKey";

	    when(userExtensionJpaRepo.findByUsername(username)).thenReturn(Optional.empty());

	    userExtensionRepository.saveUserCredentials(username, secretKey, 0, null);

	    verify(userExtensionJpaRepo, times(1)).save(Mockito.any(UserExtensionJpa.class));
	}
}