package pt.isep.desofs.m1a.g1.service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.bean.RegisterRequest;

public interface AuthenticationService {

	public AuthenticationResponse authenticate(AuthenticationRequest request);
	
	public AuthenticationResponse register(RegisterRequest request);
	
	public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException; 

}
