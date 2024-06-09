package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import pt.isep.desofs.m1a.g1.model.monitoring.Monitoring;
import pt.isep.desofs.m1a.g1.repository.MonitoringRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.MonitoringJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.MonitoringJpaMapper;

@Repository
@Profile("jpa")
public class MonitoringJpaRepositoryImpl implements MonitoringRepository {

	@Autowired
	private MonitoringJpaRepo repo;

	private MonitoringJpaMapper mapper = MonitoringJpaMapper.INSTANCE;

	@Override
	public void save(Monitoring monitoring) {
		repo.save(mapper.toDatabaseEntity(monitoring));
	}
}