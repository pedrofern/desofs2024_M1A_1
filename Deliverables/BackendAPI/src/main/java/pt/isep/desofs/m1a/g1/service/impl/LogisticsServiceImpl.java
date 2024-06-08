package pt.isep.desofs.m1a.g1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LogisticsServiceImpl implements LogisticsService {

    private final PackagingRepository packagingRepo;

    private final DeliveryRepository deliveryRepo;

    @Override
    public PackagingDto submitForm(SubmitLogisticsForm request) {

        Localization localization = new Localization(request.getX(), request.getY(), request.getZ());
        Packaging packaging = new Packaging(request.getPackagingId(), request.getDeliveryId(), request.getTruckId(), request.getLoadTime(), request.getUnloadTime(), localization.getX(), localization.getY(), localization.getZ());

        Optional<Packaging> p = packagingRepo.findByPackagingId(request.getPackagingId());
        if (p.isPresent()) {
            throw new IllegalArgumentException("PackagingId already exists");
        }
        Delivery delivery = deliveryRepo.findByDeliveryId(request.getDeliveryId());
        List<Packaging> packagesByDelivery = packagingRepo.findByDelivery(delivery);
        for (Packaging packageDelivery : packagesByDelivery) {
            if (packageDelivery.getLocalization().equals(localization) && packageDelivery.getTruckId() == request.getTruckId()) {
                throw new IllegalArgumentException("Localization and TruckId are not unique for the given deliveryId");
            }
        }

        packagingRepo.save(packaging);
        return packaging.convertToDTO();
    }

    //Get all the packaging
    @Override
    public List<Packaging> getAllPackaging() {
        return packagingRepo.findAll();
    }

    @Override
    public List<PackagingDto> getAllPackaging(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        Page<Packaging> page = packagingRepo.findAllWithFilters(pageIndex,pageSize,sortBy,sortOrder,filters);
        return page.stream().map(Packaging::convertToDTO).collect(Collectors.toList());
    }

    //Get the packaging by id
    @Override
    public Packaging getPackagingById(String id) {
        return packagingRepo.findByPackagingId(id).orElseThrow();
    }


}
