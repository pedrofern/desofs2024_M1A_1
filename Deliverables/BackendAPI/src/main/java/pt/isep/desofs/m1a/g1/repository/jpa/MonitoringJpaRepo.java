package pt.isep.desofs.m1a.g1.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.isep.desofs.m1a.g1.repository.jpa.model.MonitoringJpa;

public interface MonitoringJpaRepo extends JpaRepository<MonitoringJpa, UUID> {

}
