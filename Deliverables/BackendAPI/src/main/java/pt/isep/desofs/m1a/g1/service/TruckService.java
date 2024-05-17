package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.TruckDto;
import java.util.List;

public interface TruckService {
    TruckDto createTruck(TruckDto truckDto);
    TruckDto updateTruck(long truckId, TruckDto truckDto);
    List<TruckDto> getAllTrucks();
    List<TruckDto> getActiveTrucks();
    TruckDto getTruck(long truckId);
}