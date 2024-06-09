package pt.isep.desofs.m1a.g1.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	private String email;
	private String password;
	private String secret;
	private Integer code;

}
