package pt.isep.desofs.m1a.g1.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pt.isep.desofs.m1a.g1.dto.*;
import pt.isep.desofs.m1a.g1.service.impl.WarehouseServiceImpl;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WarehouseControllerTest {

    @Mock
    private WarehouseServiceImpl warehouseService;

    @InjectMocks
    private WarehouseController warehouseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static WarehouseDto getWarehouseDto() {
        return new WarehouseDto(
                1L,
                "Warehouse Test",
                "Rua Fernando Maurício",
                "30",
                "Lisboa",
                "Portugal",
                "1950-449",
                38.748406,
                -9.102984,
                true
        );
    }

    private static UpdateWarehouseDto getUpdateWarehouseDto() {
        return new UpdateWarehouseDto(
                "Warehouse Test",
                "Rua Fernando Maurício",
                "30",
                "Lisboa",
                "Portugal",
                "1950-449",
                38.748406,
                -9.102984,
                true
        );
    }

    @Test
    void testCreateWarehouse() {
        CreateWarehouseDto createDTO = new CreateWarehouseDto(1L, "Warehouse Test", 38.748406, -9.102984);
        WarehouseDto warehouse = getWarehouseDto();
        when(warehouseService.createWarehouse(createDTO)).thenReturn(warehouse);

        ResponseEntity<WarehouseDto> response = warehouseController.createWarehouse(createDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(warehouse, response.getBody());
    }

    @Test
    void testEditWarehouse() {
        UpdateWarehouseDto updateDTO = getUpdateWarehouseDto();
        WarehouseDto warehouse = getWarehouseDto();
        when(warehouseService.updateWarehouse(1L, updateDTO)).thenReturn(warehouse);

        ResponseEntity<WarehouseDto> response = warehouseController.editWarehouse(1L, updateDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(warehouse, response.getBody());
    }

    @Test
    public void testGetWarehouseById() throws Exception {
        WarehouseDto warehouse = getWarehouseDto();
        when(warehouseService.findWarehouseByIdentifier(1L)).thenReturn(warehouse);

        ResponseEntity<WarehouseDto> response = warehouseController.getWarehouseById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(warehouse, response.getBody());
    }

    @Test
    public void testGetAllWarehouses() throws Exception {
        WarehouseDto warehouse = getWarehouseDto();
        List<WarehouseDto> warehouseDtoList = List.of(warehouse);
        when(warehouseService.findAllWarehouses()).thenReturn(warehouseDtoList);

        ResponseEntity<List<WarehouseDto>> response = warehouseController.getAllWarehouses();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(warehouseDtoList, response.getBody());
    }

    @Test
    public void testGetWarehousesWithFilters() throws Exception {
        WarehouseDto warehouse = getWarehouseDto();
        List<WarehouseDto> warehouseDtoList = List.of(warehouse);
        when(warehouseService.findWarehouses(0, 5, "warehouseid", "asc", Map.of())).thenReturn(warehouseDtoList);

        ResponseEntity<List<WarehouseDto>> response = warehouseController.getWarehouses(0, 5, "warehouseid", "asc", Map.of());

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(warehouseDtoList, response.getBody());
    }
}
