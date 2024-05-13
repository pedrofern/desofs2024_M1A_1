package pt.isep.desofs.m1a.g1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.model.BatteryJpa;

import java.util.UUID;

public interface BatteryJpaRepo extends JpaRepository<BatteryJpa, String> {
}