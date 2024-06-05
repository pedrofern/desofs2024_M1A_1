package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.repository.UserRepository;

@Component
@Profile("bootstrap")
@Order(1)
public class UserBootstrapper implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {

		// admin
		if (userRepo.findByEmail("admin@isep.pt").isEmpty()) {
			User admin = new User("Admin Name", "Admin Name", "+3519123456789", "admin@isep.pt",
					encoder.encode("Admin@123"), "ADMIN");

			userRepo.save(admin);
		}

		// warehouse
		if (userRepo.findByEmail("warehouse@isep.pt").isEmpty()) {
			User warehouse = new User("Warehouse Name", "Warehouse Name", "+3519123456789", "warehouse@isep.pt",
					encoder.encode("Warehouse@123"), "WAREHOUSE_MANAGER");

			userRepo.save(warehouse);
		}

		// fleet
		if (userRepo.findByEmail("fleet@isep.pt").isEmpty()) {
			User fleet = new User("Fleet Name", "Fleet Name", "+3519123456789", "fleet@isep.pt",
					encoder.encode("Fleet@123"), "FLEET_MANAGER");

			userRepo.save(fleet);
		}

		// logistics
		if (userRepo.findByEmail("logistics@isep.pt").isEmpty()) {
			User logistics = new User("Logistics Name", "Logistics Name", "+3519123456789", "logistics@isep.pt",
					encoder.encode("Logistics@123"), "LOGISTICS_MANAGER");

			userRepo.save(logistics);
		}

		// operator
		if (userRepo.findByEmail("operator@isep.pt").isEmpty()) {
			User operator = new User("Operator Name", "Operator Name", "+3519123456789", "operator@isep.pt",
					encoder.encode("Operator@123"), "OPERATOR");

			userRepo.save(operator);
		}

		// admin - Cristiano Soares
		if (userRepo.findByEmail("1191816@isep.ipp.pt").isEmpty()) {
			User admin = new User("Cristiano", "Soares", "+351912123123", "1191816@isep.ipp.pt",
					encoder.encode("Admin@123"), "ADMIN");

			userRepo.save(admin);
		}

		// admin - Fábio Cruz
		if (userRepo.findByEmail("1171540@isep.ipp.pt").isEmpty()) {
			User admin = new User("Fabio", "Cruz", "+351912123123", "1171540@isep.ipp.pt",
					encoder.encode("Admin@123"), "ADMIN");

			userRepo.save(admin);
		}
	}
}
