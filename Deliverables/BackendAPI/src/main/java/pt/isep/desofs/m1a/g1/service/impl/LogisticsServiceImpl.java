package pt.isep.desofs.m1a.g1.service.impl;

import lombok.RequiredArgsConstructor;
import org.owasp.encoder.Encode;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.logistics.Time;
import pt.isep.desofs.m1a.g1.repository.DeliveryRepository;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class LogisticsServiceImpl implements LogisticsService {

    private final PackagingRepository packagingRepo;

    private final DeliveryRepository deliveryRepo;

    @Override
    public void submitForm(SubmitLogisticsForm request) {

        // Sanitize input
        String packagingId = sanitizeInput(request.getPackagingId());

        Localization localization = new Localization(request.getX(), request.getY(), request.getZ());
        Packaging packaging = new Packaging(packagingId, request.getDeliveryId(), request.getTruckId(), request.getLoadTime(), request.getUnloadTime(), localization.getX(), localization.getY(), localization.getZ());

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
    }

    //Get all the packaging
    public List<Packaging> getAllPackaging() {
        return packagingRepo.findAll();
    }

    //Get the packaging by id
    public Packaging getPackagingById(String id) {
        return packagingRepo.findByPackagingId(id).orElseThrow();
    }

    public String sanitizeInput(String input) {

        // Replace characters that may be interpreted as HTML or JavaScript with their HTML entity equivalents
        input = input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#39;")
                .replaceAll("script", "&#115cript");

        return Encode.forHtml(input);

    }


}
