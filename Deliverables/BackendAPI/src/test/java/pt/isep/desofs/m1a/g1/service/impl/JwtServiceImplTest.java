
package pt.isep.desofs.m1a.g1.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.Key;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import pt.isep.desofs.m1a.g1.model.user.User;

public class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Mock
    private UserDetails userDetails;
    
    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 3600000L); // 1 hour in milliseconds
        ReflectionTestUtils.setField(jwtService, "refreshExpiration", 86400000L); // 24 hours in milliseconds
        
        // Generate a secure secret key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secureSecretKey = Encoders.BASE64.encode(key.getEncoded());

        ReflectionTestUtils.setField(jwtService, "secretKey", secureSecretKey);
        
        String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+1234567890";
		String email = "john@example.com";
		String password = "Test@1234";
		String role = "ADMIN";

		user = new User(firstName, lastName, phoneNumber, email, password, role, false);
    }

    @Test
    public void testExtractUsername() {
        UserDetails userDetails = user;
        String token = jwtService.generateToken(userDetails);
        String username = jwtService.extractUsername(token);
        assertEquals("john@example.com", username);
    }

    @Test
    public void testExtractClaim() {
        UserDetails userDetails = user;
        String token = jwtService.generateToken(userDetails);
        Date expiration = jwtService.extractClaim(token, Claims::getExpiration);
        assertNotNull(expiration);
    }

    @Test
    public void testGenerateToken() {
        UserDetails userDetails = user;
        String token = jwtService.generateToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void testGenerateRefreshToken() {
        UserDetails userDetails = user;
        String token = jwtService.generateRefreshToken(userDetails);
        assertNotNull(token);
    }

    @Test
    public void testIsTokenValid() {
        UserDetails userDetails = user;
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

}
