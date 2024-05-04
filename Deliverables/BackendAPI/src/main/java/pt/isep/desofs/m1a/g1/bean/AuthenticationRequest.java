package pt.isep.desofs.m1a.g1.bean;

import lombok.Data;

@Data
public class AuthenticationRequest {
	private String email;
	private String password;

}
