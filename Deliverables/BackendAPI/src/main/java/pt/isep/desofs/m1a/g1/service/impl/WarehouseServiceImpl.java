package pt.isep.desofs.m1a.g1.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pt.isep.desofs.m1a.g1.dto.GeolocalizacaoResponseDTO;
import pt.isep.desofs.m1a.g1.dto.WarehouseDto;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.service.WarehouseService;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    private final WarehouseRepository warehouseRepository;

    @Autowired
    private GeolocalizacaoService geoService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WarehouseDto createWarehouse(WarehouseDto warehouseDto) {
//        Warehouse warehouse = convertToEntity(deliveryDTO);
//        delivery = deliveryRepository.save(delivery);
//        return convertToDTO(delivery);
        return null;
    }

}
