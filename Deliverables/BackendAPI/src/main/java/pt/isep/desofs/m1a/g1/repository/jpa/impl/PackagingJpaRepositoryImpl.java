package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.PackagingJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.PackagingJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jpa")
public class PackagingJpaRepositoryImpl implements PackagingRepository {

    @Autowired
    private PackagingJpaRepo repo;

    private PackagingJpaMapper mapper = PackagingJpaMapper.INSTANCE;

    @Override
    public Optional<Packaging> findByPackagingId(String id) {

        PackagingJpa p = repo.findByPackagingId(id);
        if (p != null) {
            return Optional.of(mapper.toDomainModel(p));
        }
        return Optional.empty();
    }

    @Override
    public List<Packaging> findByDeliveryId(long deliveryId) {

            List<PackagingJpa> packagingJpaList = repo.findByDeliveryId(deliveryId);
            if (!packagingJpaList.isEmpty()) {
                return packagingJpaList.stream()
                        .map(mapper::toDomainModel)
                        .collect(Collectors.toList());
            }
            return new ArrayList<>();
    }

    @Override
    public List<Packaging> findAll() {

        List<PackagingJpa> packagingJpaList = repo.findAll();
        if (!packagingJpaList.isEmpty()) {
            return packagingJpaList.stream()
                    .map(mapper::toDomainModel)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Packaging packaging) {
        PackagingJpa savedPackaging = repo.save(mapper.toDatabaseEntity(packaging));
    }
}
