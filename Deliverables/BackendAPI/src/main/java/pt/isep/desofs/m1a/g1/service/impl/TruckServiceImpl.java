package pt.isep.desofs.m1a.g1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pt.isep.desofs.m1a.g1.config.InputSanitizer;
import pt.isep.desofs.m1a.g1.dto.BatteryDto;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.TruckDto;
import pt.isep.desofs.m1a.g1.exception.InvalidBatteryException;
import pt.isep.desofs.m1a.g1.exception.InvalidTruckException;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.truck.Battery;
import pt.isep.desofs.m1a.g1.model.truck.Truck;
import pt.isep.desofs.m1a.g1.repository.TruckRepository;
import pt.isep.desofs.m1a.g1.service.TruckService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    private TruckRepository truckRepository;

    @Override
    public TruckDto createTruck(TruckDto truckDto) {
        validateTruckDto(truckDto);
        if (truckDto.getBattery() == null) {
            throw new InvalidBatteryException("Battery information cannot be null");
        }
        if (truckDto.getTare() < 0) {
            throw new InvalidTruckException("Tare must be positive");
        }
        Truck truck = convertToEntity(truckDto);
        Truck savedTruck = truckRepository.save(truck);
        return convertToDto(savedTruck);
    }

    @Override
    public TruckDto updateTruck(long truckId, TruckDto truckDto) {
        validateTruckDto(truckDto);
        Truck truck = truckRepository.findByTruckId(truckId).orElse(null);
        if (truck == null) {
            throw new InvalidTruckException("Truck not found");
        }
        if (truckDto.getBattery() == null) {
            throw new InvalidBatteryException("Battery information cannot be null");
        }
        if (truckDto.getTare() < 0) {
            throw new InvalidTruckException("Tare must be positive");
        }
        truck.setActive(truckDto.isActive());
        truck.setTare(truckDto.getTare());
        truck.setLoadCapacity(truckDto.getLoadCapacity());
        truck.getBattery().setAutonomy(truckDto.getBattery().getAutonomy());
        truck.getBattery().setChargingTime(truckDto.getBattery().getChargingTime());
        Truck updatedTruck = truckRepository.save(truck);
        return convertToDto(updatedTruck);
    }

    @Override
    public List<TruckDto> getAllTrucks() {
        return truckRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TruckDto> getTrucks(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters) {
        return truckRepository.findAllWithFilters(pageIndex, pageSize, sortBy, sortOrder, filters).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TruckDto> getActiveTrucks() {
        return truckRepository.findAllActive().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TruckDto getTruck(long truckId) {
        validateTruckId(truckId);
        Truck truck = truckRepository.findByTruckId(truckId).orElse(null);
        if (truck == null) {
            throw new InvalidTruckException("Truck not found");
        }
        return convertToDto(truck);
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
