package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pt.isep.desofs.m1a.g1.model.user.Token;
import pt.isep.desofs.m1a.g1.model.user.TokenType;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.jpa.TokenJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.UserJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TokenJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.UserJpa;

public class TokenJpaRepositoryImplTest {

    @InjectMocks
    private TokenJpaRepositoryImpl tokenJpaRepository;

    @Mock
    private TokenJpaRepo tokenJpaRepo;

    @Mock
    private UserJpaRepo userJpaRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllValidTokenByUser() {
        String email = "john@example.com";

        TokenJpa tokenJpa1 = new TokenJpa();
        tokenJpa1.setId(UUID.randomUUID());
        tokenJpa1.setToken("token1");

        TokenJpa tokenJpa2 = new TokenJpa();
        tokenJpa2.setId(UUID.randomUUID());
        tokenJpa2.setToken("token2");

        List<TokenJpa> tokenJpaList = Arrays.asList(tokenJpa1, tokenJpa2);

        when(tokenJpaRepo.findAllValidTokenByUser(email)).thenReturn(tokenJpaList);

        List<Token> result = tokenJpaRepository.findAllValidTokenByUser(email);

        verify(tokenJpaRepo, times(1)).findAllValidTokenByUser(email);
        assertEquals(2, result.size());
        assertEquals("token1", result.get(0).getToken());
        assertEquals("token2", result.get(1).getToken());
    }

    @Test
    public void testFindByToken() {
        String tokenStr = "token1";

        TokenJpa tokenJpa = new TokenJpa();
        tokenJpa.setId(UUID.randomUUID());
        tokenJpa.setToken(tokenStr);

        when(tokenJpaRepo.findByToken(tokenStr)).thenReturn(Optional.of(tokenJpa));

        Optional<Token> result = tokenJpaRepository.findByToken(tokenStr);

        verify(tokenJpaRepo, times(1)).findByToken(tokenStr);
        assertEquals(tokenStr, result.get().getToken());
    }

    @Test
    public void testSave() {
        String email = "john@example.com";
        String tokenStr = "token1";
        UUID tokenId = UUID.randomUUID();

        User user = new User("John", "Doe", "+1234567890", email, "Test@1234", "ADMIN");
        Token token = new Token(tokenId, tokenStr, TokenType.BEARER, false, false,user);

        UserJpa userJpa = new UserJpa();
        userJpa.setEmail(email);

        TokenJpa tokenJpa = new TokenJpa();
        tokenJpa.setId(tokenId);
        tokenJpa.setToken(tokenStr);
        tokenJpa.setUser(userJpa);

        when(userJpaRepo.findByEmail(email)).thenReturn(Optional.of(userJpa));
        when(tokenJpaRepo.save(tokenJpa)).thenReturn(tokenJpa);

        Token result = tokenJpaRepository.save(token);

        verify(userJpaRepo, times(1)).findByEmail(email);
        verify(tokenJpaRepo, times(1)).save(tokenJpa);
        assertEquals(tokenStr, result.getToken());
    }

    @Test
    public void testSaveAll() {
        String email = "john@example.com";
        UUID tokenId1 = UUID.randomUUID();
        UUID tokenId2 = UUID.randomUUID();
        String tokenStr1 = "token1";
        String tokenStr2 = "token2";

        User user1 = new User("John", "Doe", "+1234567890", email, "Test@1234", "ADMIN");
        User user2 = new User("John", "Doe", "+1234567890", email, "Test@1234", "ADMIN");
        Token token1 = new Token(tokenId1, tokenStr1,TokenType.BEARER, false, false, user1);
        Token token2 = new Token(tokenId2, tokenStr2, TokenType.BEARER, false, false,user2);

        UserJpa userJpa = new UserJpa();
        userJpa.setEmail(email);

        TokenJpa tokenJpa1 = new TokenJpa();
        tokenJpa1.setId(tokenId1);
        tokenJpa1.setToken(tokenStr1);
        tokenJpa1.setUser(userJpa);

        TokenJpa tokenJpa2 = new TokenJpa();
        tokenJpa2.setId(tokenId2);
        tokenJpa2.setToken(tokenStr2);
        tokenJpa2.setUser(userJpa);

        when(userJpaRepo.findByEmail(email)).thenReturn(Optional.of(userJpa));
        when(tokenJpaRepo.save(tokenJpa1)).thenReturn(tokenJpa1);
        when(tokenJpaRepo.save(tokenJpa2)).thenReturn(tokenJpa2);

        tokenJpaRepository.saveAll(Arrays.asList(token1, token2));

        verify(userJpaRepo, times(2)).findByEmail(email);
        verify(tokenJpaRepo, times(1)).save(tokenJpa1);
        verify(tokenJpaRepo, times(1)).save(tokenJpa2);
    }
}
