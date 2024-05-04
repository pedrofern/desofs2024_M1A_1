package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.UserRepository;

@Component
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		// admin
		if (userRepo.findByEmail("admin@isep.pt").isEmpty()) {
			User admin = new User("First Name", "Last Name", "+3519123456789", "admin@isep.pt",
					encoder.encode("Admin@123"), "ADMIN");

			userRepo.save(admin);
		}
	}
}
