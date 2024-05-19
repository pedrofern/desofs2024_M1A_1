package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;
import pt.isep.desofs.m1a.g1.repository.jpa.DeliveryJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.PackagingJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.TruckJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.DeliveryJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.PackagingJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.TruckJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.PackagingJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.TruckJpa;

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

    private DeliveryJpaMapper deliveryJpaMapper = DeliveryJpaMapper.INSTANCE;

    private TruckJpaMapper truckJpaMapper = TruckJpaMapper.INSTANCE;

    @Autowired
    private DeliveryJpaRepo deliveryRepository;

    @Autowired
    private TruckJpaRepo truckRepository;

    @Override
    public Optional<Packaging> findByPackagingId(String id) {

        PackagingJpa p = repo.findByPackagingId(id);
        if (p != null) {
            return Optional.of(mapper.toDomainModel(p));
        }
        return Optional.empty();
    }

    @Override
    public List<Packaging> findByDelivery(Delivery delivery) {
            List<PackagingJpa> packagingJpaList = repo.findByDelivery_DeliveryId(delivery.getDeliveryId());
            if (!packagingJpaList.isEmpty()) {
                return packagingJpaList.stream()
                        .map(mapper::toDomainModel)
                        .collect(Collectors.toList());
            }
            return new ArrayList<>();
    }

    @Override
    public List<Packaging> findAll() {
        List<Packaging> list = new ArrayList<>();
        List<PackagingJpa> packagingJpaList = repo.findAll();
        if (!packagingJpaList.isEmpty()) {
            for (PackagingJpa packagingJpa : packagingJpaList){
                Packaging p = new Packaging(packagingJpa.getPackagingId(), packagingJpa.getDelivery().getDeliveryId(), packagingJpa.getTruck().getTruckId(), packagingJpa.getLoadTime(), packagingJpa.getUnloadTime(), packagingJpa.getX(), packagingJpa.getY(), packagingJpa.getZ());
                list.add(p);
            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public void save(Packaging packaging) {
        Optional<DeliveryJpa> deliveryJpa =deliveryRepository.findByDeliveryId(packaging.getDeliveryId());
        if (deliveryJpa.isEmpty()) {
            throw new IllegalArgumentException("Delivery not found with identifier: " + packaging.getDeliveryId());
        }
        Optional<TruckJpa> truckJpa = truckRepository.findByTruckId(packaging.getTruckId());
        if (truckJpa.isEmpty()){
            throw new IllegalArgumentException("Truck not found with identifier: " + packaging.getTruckId());
        }
        PackagingJpa packagingJpa = mapper.toDatabaseEntity(packaging);
        packagingJpa.setDelivery(deliveryJpa.get());
        packagingJpa.setTruck(truckJpa.get());
        repo.save(packagingJpa);
    }
}
