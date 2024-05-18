package pt.isep.desofs.m1a.g1.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	private String role;
}
