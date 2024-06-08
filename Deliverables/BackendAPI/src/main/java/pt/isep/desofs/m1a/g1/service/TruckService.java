package pt.isep.desofs.m1a.g1.service;

import pt.isep.desofs.m1a.g1.dto.TruckDto;
import java.util.List;
import java.util.Map;

public interface TruckService {
    TruckDto createTruck(TruckDto truckDto);
    TruckDto updateTruck(long truckId, TruckDto truckDto);
    List<TruckDto> getAllTrucks();
    List<TruckDto> getTrucks(int pageIndex, int pageSize, String sortBy, String sortOrder, Map<String, String> filters);
    List<TruckDto> getActiveTrucks();
    TruckDto getTruck(long truckId);
}