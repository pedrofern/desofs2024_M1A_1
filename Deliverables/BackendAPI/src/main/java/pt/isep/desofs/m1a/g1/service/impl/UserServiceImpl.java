package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.service.UserService;
import pt.isep.desofs.m1a.g1.validator.PasswordValidator;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean register(RegisterRequest request) {
		try {
			var password = PasswordValidator.createPassword(request.getPassword());
			String passwordEncoded = passwordEncoder.encode(password);
			var user = new User(request.getFirstName(), request.getLastName(), request.getPhoneNumber(), request.getEmail(),
					passwordEncoded, request.getRole());
			var savedUser = userRepo.save(user);
			return savedUser != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean assignNewRole(String id, String role) {
		try {			
			var user = userRepo.findByEmail(id).orElseThrow();
			if (user == null)
				return false;
			
			user.assignNewRole(role);
			var savedUser = userRepo.save(user);
			return savedUser != null;
			
		} catch (Exception e) {
			return false;
		}
	}

}
