
package pt.isep.desofs.m1a.g1.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.TokenRepository;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.service.JwtService;

public class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest();
        String email = "testEmail@batatas.pt";
		request.setEmail(email);
        String password = "Pass@1234";
		request.setPassword(password);
        User user = new User("testFirstName", "testLastName", "911234567", email, password, "ADMIN");
        String jwtToken = "testToken";
        String refreshToken = "refreshToken";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);

        authenticationService.authenticate(request);

        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(email, password));
        verify(userRepository, times(1)).findByEmail(email);
        verify(jwtService, times(1)).generateToken(user);
        verify(jwtService, times(1)).generateRefreshToken(user);
    }
}
