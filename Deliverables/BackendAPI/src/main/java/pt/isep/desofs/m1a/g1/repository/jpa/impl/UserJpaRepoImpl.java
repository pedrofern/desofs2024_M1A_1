package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pt.isep.desofs.m1a.g1.repository.UserRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.UserJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.UserJpaMapper;

@Repository
public class UserJpaRepoImpl implements UserRepo {
	
	@Autowired
	private UserJpaRepo repo;

	private UserJpaMapper mapper = UserJpaMapper.INSTANCE;

}
