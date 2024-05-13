package pt.isep.desofs.m1a.g1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.logistics.Time;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogisticsServiceImpl implements LogisticsService {

    private final PackagingRepository packagingRepo;

    @Override
    public void submitForm(SubmitLogisticsForm request) {
        Time loadTime = new Time(request.getLoadTime());
        Time unloadTime = new Time(request.getUnloadTime());
        Localization localization = new Localization(request.getX(), request.getY(), request.getZ());
        Packaging packaging = new Packaging(request.getPackagingId(), request.getDeliveryId(), request.getTruckId(), loadTime.getValue(), unloadTime.getValue(), localization.getX(),localization.getY(),localization.getZ());

        Optional<Packaging> p = packagingRepo.findByPackagingId(request.getPackagingId());
        if (p.isPresent()) {
            throw new IllegalArgumentException("PackagingId already exists");
        }
        List<Packaging> packagesByDelivery = packagingRepo.findByDeliveryId(request.getDeliveryId());
        for (Packaging packageDelivery : packagesByDelivery) {
            if (packageDelivery.getLocalization().equals(localization) && packageDelivery.getTruckId() == request.getTruckId()) {
                throw new IllegalArgumentException("Localization and TruckId are not unique for the given deliveryId");
            }
        }

        packagingRepo.save(packaging);
    }

    //Get all the packaging
    public List<Packaging> getAllPackaging() {
        return packagingRepo.findAll();
    }

    //Get the packaging by id
public Packaging getPackagingById(long id) {
        return packagingRepo.findByPackagingId(id).orElseThrow();
    }
}
