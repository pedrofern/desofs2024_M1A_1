package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isep.desofs.m1a.g1.config.InputSanitizer;
import pt.isep.desofs.m1a.g1.dto.BatteryDto;
import pt.isep.desofs.m1a.g1.dto.TruckDto;
import pt.isep.desofs.m1a.g1.exception.InvalidBatteryException;
import pt.isep.desofs.m1a.g1.exception.InvalidTruckException;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;
import pt.isep.desofs.m1a.g1.service.TruckService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TruckServiceImpl implements TruckService {

    private static final Logger logger = LoggerFactory.getLogger(TruckServiceImpl.class);

    @Autowired
    private TruckRepository truckRepository;

    @Override
    @Transactional
    public TruckDto createTruck(TruckDto truckDto) {
        try {
            validateTruckDto(truckDto);
            Truck truck = convertToEntity(truckDto);
            Truck savedTruck = truckRepository.save(truck);
            return convertToDto(savedTruck);
        } catch (InvalidBatteryException | InvalidTruckException e) {
            logger.error("Error creating truck: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error creating truck", e);
            throw new RuntimeException("Unexpected error occurred. Please try again later.");
        }
    }

    @Override
    @Transactional
    public TruckDto updateTruck(long truckId, TruckDto truckDto) {
        try {
            validateTruckDto(truckDto);
            Truck truck = truckRepository.findByTruckId(truckId).orElseThrow(() -> new InvalidTruckException("Truck not found"));
            truck.setActive(truckDto.isActive());
            truck.setTare(truckDto.getTare());
            truck.setLoadCapacity(truckDto.getLoadCapacity());
            truck.getBattery().setAutonomy(truckDto.getBattery().getAutonomy());
            truck.getBattery().setChargingTime(truckDto.getBattery().getChargingTime());
            Truck updatedTruck = truckRepository.save(truck);
            return convertToDto(updatedTruck);
        } catch (InvalidBatteryException | InvalidTruckException e) {
            logger.error("Error updating truck: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error updating truck", e);
            throw new RuntimeException("Unexpected error occurred. Please try again later.");
        }
    }

    @Override
    public List<TruckDto> getAllTrucks() {
        try {
            return truckRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Unexpected error fetching all trucks", e);
            throw new RuntimeException("Unexpected error occurred. Please try again later.");
        }
    }

    @Override
    public List<TruckDto> getTrucks(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        try {
            return truckRepository.findAllWithFilters(pageIndex, pageSize, sortBy, sortOrder, filters).stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Unexpected error fetching trucks with filters", e);
            throw new RuntimeException("Unexpected error occurred. Please try again later.");
        }
    }

    @Override
    public List<TruckDto> getActiveTrucks() {
        try {
            return truckRepository.findAllActive().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Unexpected error fetching active trucks", e);
            throw new RuntimeException("Unexpected error occurred. Please try again later.");
        }
    }

    @Override
    public TruckDto getTruck(long truckId) {
        try {
            validateTruckId(truckId);
            Truck truck = truckRepository.findByTruckId(truckId).orElseThrow(() -> new InvalidTruckException("Truck not found"));
            return convertToDto(truck);
        } catch (InvalidTruckException e) {
            logger.error("Error fetching truck: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error fetching truck", e);
            throw new RuntimeException("Unexpected error occurred. Please try again later.");
        }
    }

    private void validateTruckId(long truckId) {
        if (InputSanitizer.containsMaliciousContent(String.valueOf(truckId))) {
            throw new InvalidTruckException("Truck ID contains potentially malicious content");
        }
    }

    private void validateTruckDto(TruckDto truckDto) {
        if (truckDto.getBattery() == null) {
            throw new InvalidBatteryException("Battery information cannot be null");
        }
        if (truckDto.getTare() < 0) {
            throw new InvalidTruckException("Tare must be positive");
        }

        if (InputSanitizer.containsMaliciousContent(String.valueOf(truckDto.getTruckId())) ||
                InputSanitizer.containsMaliciousContent(String.valueOf(truckDto.getTare())) ||
                InputSanitizer.containsMaliciousContent(String.valueOf(truckDto.getLoadCapacity())) ||
                InputSanitizer.containsMaliciousContent(String.valueOf(truckDto.isActive())) ||
                InputSanitizer.containsMaliciousContent(truckDto.getBattery().toString())) {
            throw new InvalidTruckException("Request contains potentially malicious content");
        }
    }

    private TruckDto convertToDto(Truck truck) {
        TruckDto truckDto = new TruckDto();
        truckDto.setTruckId(truck.getTruckId());
        truckDto.setBattery(convertToDto(truck.getBattery()));
        truckDto.setActive(truck.isActive());
        truckDto.setTare(truck.getTare());
        truckDto.setLoadCapacity(truck.getLoadCapacity());
        return truckDto;
    }

    private BatteryDto convertToDto(Battery battery) {
        BatteryDto batteryDto = new BatteryDto();
        batteryDto.setBatteryId(battery.getBatteryId());
        batteryDto.setAutonomy(battery.getAutonomy());
        batteryDto.setMaximumBattery(battery.getMaximumBattery());
        batteryDto.setChargingTime(battery.getChargingTime());
        return batteryDto;
    }

    private Truck convertToEntity(TruckDto truckDto) {
        Battery b = new Battery(truckDto.getBattery().getBatteryId(), truckDto.getBattery().getAutonomy(),
                truckDto.getBattery().getMaximumBattery(), truckDto.getBattery().getChargingTime());
        return new Truck(truckDto.getTruckId(), truckDto.getTare(), truckDto.getLoadCapacity(), truckDto.isActive(), b);
    }
}
