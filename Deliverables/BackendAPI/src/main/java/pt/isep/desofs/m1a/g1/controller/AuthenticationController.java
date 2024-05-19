package pt.isep.desofs.m1a.g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.service.AuthenticationService;

@Tag(name = "Authentication")
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/api/v1/user/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}

}
