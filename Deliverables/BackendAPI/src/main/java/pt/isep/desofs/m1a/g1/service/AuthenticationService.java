package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;

public interface AuthenticationService {

	public AuthenticationResponse authenticate(AuthenticationRequest request);

}
