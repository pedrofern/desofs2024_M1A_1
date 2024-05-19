package pt.isep.desofs.m1a.g1.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
	private String email;
	private String password;

}
