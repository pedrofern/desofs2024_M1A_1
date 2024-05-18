package pt.isep.desofs.m1a.g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import pt.isep.desofs.m1a.g1.bean.AssignNewRoleRequest;
import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.service.UserService;

@Tag(name = "User")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/v1/user/register")
	public ResponseEntity<Boolean> register(@RequestBody RegisterRequest request) {
		return userService.register(request) ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
	}

	@PutMapping("/api/v1/user/{id}/assign-role")
	public ResponseEntity<Boolean> assignNewRole(@PathVariable String id, @RequestBody AssignNewRoleRequest request) {
		return userService.assignNewRole(id, request.getRole()) ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
	}

}
