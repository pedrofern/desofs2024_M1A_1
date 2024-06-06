package pt.isep.desofs.m1a.g1.service;

import java.util.List;

import pt.isep.desofs.m1a.g1.bean.RegisterRequest;
import pt.isep.desofs.m1a.g1.dto.UserDto;

public interface UserService {

	public boolean register(RegisterRequest request);

	public boolean assignNewRole(String id, String role);

	public List<UserDto> getAllUsers();
	
	public UserDto getUser(String email);

}
