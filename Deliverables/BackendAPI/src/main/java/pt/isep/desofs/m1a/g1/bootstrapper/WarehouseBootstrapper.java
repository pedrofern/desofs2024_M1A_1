package pt.isep.desofs.m1a.g1.bootstrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pt.isep.desofs.m1a.g1.dto.CreateWarehouseDto;
import pt.isep.desofs.m1a.g1.model.logistics.Localization;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.PackagingRepository;
import pt.isep.desofs.m1a.g1.repository.WarehouseRepository;
import pt.isep.desofs.m1a.g1.service.LogisticsService;
import pt.isep.desofs.m1a.g1.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@Component
@Profile("bootstrap")
public class WarehouseBootstrapper implements CommandLineRunner {

    @Autowired
    private WarehouseRepository warehouseRepo;

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public void run(String... args) throws Exception {
        List<Warehouse> warehouses = warehouseRepo.findAll();
        if (warehouses.isEmpty()) {
            CreateWarehouseDto createWarehouseDto = new CreateWarehouseDto(1, "Warehouse 1", 41.35741780756182, -8.562390204464108);
            warehouseService.createWarehouse(createWarehouseDto);
        }

    }
}
