package pt.isep.desofs.m1a.g1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pt.isep.desofs.m1a.g1.bean.AssignNewRoleRequest;
import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.dto.UserDto;
import pt.isep.desofs.m1a.g1.model.user.Role;
import pt.isep.desofs.m1a.g1.service.UserService;

@Tag(name = "User")
@RestController
public class UserController {
	
	private static final String ROLE = "ROLE_"+Role.ADMIN;

	@Autowired
	private UserService userService;

	@PostMapping("/api/v1/user/register")
	public ResponseEntity<Boolean> register(@RequestBody RegisterRequest request) {
		return userService.register(request) ? new ResponseEntity<>(true, HttpStatus.CREATED)
				: new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/api/v1/user/{id}/assign-role")
	public ResponseEntity<Boolean> assignNewRole(@PathVariable String id, @RequestBody AssignNewRoleRequest request) {
		return userService.assignNewRole(id, request.getRole()) ? new ResponseEntity<>(true, HttpStatus.OK)
				: new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/api/v1/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authenticatedEmail;
		String authenticatedRole;

		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			authenticatedEmail = userDetails.getUsername();
			authenticatedRole = userDetails.getAuthorities().stream().map(a -> a.getAuthority())
					.collect(Collectors.joining());
		} else {
			authenticatedEmail = principal.toString();
			authenticatedRole = "";
		}

		if (!ROLE.equals(authenticatedRole)) {
			return new ResponseEntity<>(List.of(getUserById(authenticatedEmail).getBody()), HttpStatus.OK);
		}
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/user/{email}")
	public ResponseEntity<UserDto> getUserById(@PathVariable String email) {
		// if the email does not match with the authenticated user, it should return a 401 Unauthorized
		// exception is if it is an admin, then it should be able to see all users		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authenticatedEmail;
		String authenticatedRole;
		

	    if (principal instanceof UserDetails) {
	        UserDetails userDetails = (UserDetails) principal;
	        authenticatedEmail = userDetails.getUsername();
	        authenticatedRole = userDetails.getAuthorities().stream()
	                                       .map(a -> a.getAuthority())
	                                       .collect(Collectors.joining());
	    } else {
	        authenticatedEmail = principal.toString();
	        authenticatedRole = "";
	    }

	    if (!email.equals(authenticatedEmail) && !ROLE.equals(authenticatedRole)) {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
		
		return new ResponseEntity<>(userService.getUser(email), HttpStatus.OK);
	}
}
