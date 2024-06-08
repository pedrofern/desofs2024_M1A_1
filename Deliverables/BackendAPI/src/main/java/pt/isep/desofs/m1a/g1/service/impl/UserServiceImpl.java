package pt.isep.desofs.m1a.g1.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.dto.UserDto;
import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.UserExtensionRepository;
import pt.isep.desofs.m1a.g1.repository.UserRepository;
import pt.isep.desofs.m1a.g1.service.UserService;
import pt.isep.desofs.m1a.g1.validator.PasswordValidator;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	private final UserExtensionRepository userExtensionRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean register(RegisterRequest request) {
		try {
			var password = PasswordValidator.createPassword(request.getPassword());
			String passwordEncoded = passwordEncoder.encode(password);
			var user = new User(request.getFirstName(), request.getLastName(), request.getPhoneNumber(),
					request.getEmail(), passwordEncoded, request.getRole(), false);
			var savedUser = userRepo.save(user);
			var userExtension = userExtensionRepo.save(new UserExtension(user.getUsername(), 0));
			return savedUser != null && userExtension != null;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean assignNewRole(String id, String role) {
		try {
			var user = userRepo.findByEmail(id).orElse(null);
			if (user == null)
				return false;

			user.assignNewRole(role);
			var savedUser = userRepo.save(user);
			return savedUser != null;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> all = userRepo.findAll();

		List<UserDto> allDto = all.stream()
				.map(user -> new UserDto(user.getUsername(), user.getFirstName().getValue(),
						user.getLastName().getValue(), user.getPhoneNumber().getValue(), user.getEmail().getValue(),
						user.getRole().getName()))
				.collect(Collectors.toList());

		return allDto;
	}

	@Override
	public UserDto getUser(String email) {
		var user = userRepo.findByEmail(email).orElse(null);
		if (user == null)
			return null;

		return new UserDto(user.getUsername(), user.getFirstName().getValue(), user.getLastName().getValue(),
				user.getPhoneNumber().getValue(), user.getEmail().getValue(), user.getRole().getName());

	}

}
