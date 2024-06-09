package pt.isep.desofs.m1a.g1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pt.isep.desofs.m1a.g1.bean.AuthenticationRequest;
import pt.isep.desofs.m1a.g1.bean.AuthenticationResponse;
import pt.isep.desofs.m1a.g1.service.AuthenticationService;
import pt.isep.desofs.m1a.g1.service.TwoFactorAuthService;

@Tag(name = "Authentication")
@RestController
public class AuthenticationController {
	
	@Value("${two.factor.auth.enabled:false}")
    private boolean twoFactorEnabled;

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private TwoFactorAuthService twoFactorAuthService;

	@GetMapping("/api/v1/user/login/generatef2a/{email}")
	public ResponseEntity<Map<String, String>> generate2FA(@PathVariable String email) {
		if (!twoFactorEnabled) {
			Map<String, String> response = new HashMap<>();
			response.put("message", "2FA is not enabled");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} 
		Pair<String,String> twoFactorPair = twoFactorAuthService.getQRBarcodeURL(email);
		Map<String, String> response = new HashMap<>();
		response.put("secret", twoFactorPair.getFirst());
		response.put("qrCodeUrl", twoFactorPair.getSecond());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/api/v1/user/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
		
		if(twoFactorEnabled && !twoFactorAuthService.validateCode(request.getSecret(), request.getCode())) {
			Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid code");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		
		try {			
			AuthenticationResponse response = authenticationService.authenticate(request);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
	}
}
