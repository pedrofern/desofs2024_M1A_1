package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.bean.RegisterRequest;

public interface UserService {

	public boolean register(RegisterRequest request);

	public boolean assignNewRole(String id, String role);

}
