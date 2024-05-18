package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.WarehouseDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.service.LogisticsService;
import pt.isep.desofs.m1a.g1.service.WarehouseService;

import java.util.List;

@Tag(name = "Warehouse")
@RestController
@RequestMapping(path = "/api/v1/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/")
    public void createWarehouse(@RequestBody WarehouseDto body) {
        warehouseService.createWarehouse(body);
    }

}
