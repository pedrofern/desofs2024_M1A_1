package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pt.isep.desofs.m1a.g1.model.user.User;
import pt.isep.desofs.m1a.g1.model.user.UserExtension;
import pt.isep.desofs.m1a.g1.repository.UserExtensionRepository;
import pt.isep.desofs.m1a.g1.repository.UserRepository;

@Component
@Profile("bootstrap")
@Order(1)
public class UserBootstrapper implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserExtensionRepository userExtensionRepo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {

		// admin
		if (userRepo.findByEmail("admin@isep.pt").isEmpty()) {
			User admin = new User("Admin Name", "Admin Name", "+351912345678", "admin@isep.pt",
					encoder.encode("Admin@123"), "ADMIN", false);

			userRepo.save(admin);
			userExtensionRepo.save(new UserExtension(admin.getUsername(), 0));
		}

		// warehouse
		if (userRepo.findByEmail("warehouse@isep.pt").isEmpty()) {
			User warehouse = new User("Warehouse Name", "Warehouse Name", "+351912345678", "warehouse@isep.pt",
					encoder.encode("Warehouse@123"), "WAREHOUSE_MANAGER", false);

			userRepo.save(warehouse);
			userExtensionRepo.save(new UserExtension(warehouse.getUsername(), 0));
		}

		// fleet
		if (userRepo.findByEmail("fleet@isep.pt").isEmpty()) {
			User fleet = new User("Fleet Name", "Fleet Name", "+351912345678", "fleet@isep.pt",
					encoder.encode("Fleet@123"), "FLEET_MANAGER", false);

			userRepo.save(fleet);
			userExtensionRepo.save(new UserExtension(fleet.getUsername(), 0));
		}

		// logistics
		if (userRepo.findByEmail("logistics@isep.pt").isEmpty()) {
			User logistics = new User("Logistics Name", "Logistics Name", "+351912345678", "logistics@isep.pt",
					encoder.encode("Logistics@123"), "LOGISTICS_MANAGER", false);

			userRepo.save(logistics);
			userExtensionRepo.save(new UserExtension(logistics.getUsername(), 0));
		}

		// operator
		if (userRepo.findByEmail("operator@isep.pt").isEmpty()) {
			User operator = new User("Operator Name", "Operator Name", "+351912345678", "operator@isep.pt",
					encoder.encode("Operator@123"), "OPERATOR", false);

			userRepo.save(operator);
			userExtensionRepo.save(new UserExtension(operator.getUsername(), 0));
		}

		// admin - Cristiano Soares
		if (userRepo.findByEmail("1191816@isep.ipp.pt").isEmpty()) {
			User cristiano = new User("Cristiano", "Soares", "+351912123123", "1191816@isep.ipp.pt",
					encoder.encode("Admin@123"), "ADMIN", false);

			userRepo.save(cristiano);
			userExtensionRepo.save(new UserExtension(cristiano.getUsername(), 0));
		}

		// admin - Fábio Cruz
		if (userRepo.findByEmail("1171540@isep.ipp.pt").isEmpty()) {
			User fabio = new User("Fabio", "Cruz", "+351912123123", "1171540@isep.ipp.pt",
					encoder.encode("Admin@123"), "ADMIN", false);

			userRepo.save(fabio);
			userExtensionRepo.save(new UserExtension(fabio.getUsername(), 0));
		}
		
		// admin - Pedro Fernandes
		if (userRepo.findByEmail("1060503@isep.pt").isEmpty()) {
			User pedro = new User("Pedro", "Fernandes", "+351912123123", "1060503@isep.pt",
					encoder.encode("Admin@123"), "ADMIN", false);

			userRepo.save(pedro);
			userExtensionRepo.save(new UserExtension(pedro.getUsername(), 0));
		}
	}
}
