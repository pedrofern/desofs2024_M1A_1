package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.WarehouseJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WarehouseJpaRepositoryImplTest {

    @Mock
    private WarehouseJpaRepo warehouseJpaRepo;

    @InjectMocks
    private WarehouseJpaRepositoryImpl warehouseJpaRepository;

    private static final WarehouseJpaMapper mapper = WarehouseJpaMapper.INSTANCE;

    private static WarehouseJpa getTestWarehouse() {
        return mapper.toDatabaseEntity(getTestWarehouse1());
    }

    private static Warehouse getTestWarehouse1() {
        return new Warehouse(
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

    private static Warehouse getTestWarehouse2() {
        return new Warehouse(
                2L,
                "Warehouse Test 2",
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

    private static List<WarehouseJpa> getTestWarehouses() {
        return Stream.of(getTestWarehouse1(), getTestWarehouse2())
                .map(mapper::toDatabaseEntity)
                .toList();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllShouldReturnAllWarehouses() {
        when(warehouseJpaRepo.findAll()).thenReturn(getTestWarehouses());
        assertEquals(2, warehouseJpaRepository.findAll().size());
    }

    @Test
    void findByIdentifierShouldReturnWarehouse() {
        when(warehouseJpaRepo.findByIdentifier(1L)).thenReturn(Optional.of(getTestWarehouse()));
        assertEquals("Warehouse Test", warehouseJpaRepository.findByIdentifier(1L).get().getDesignation());
    }

    @Test
    void saveShouldReturnSavedWarehouse() {
        when(warehouseJpaRepo.save(any())).thenReturn(getTestWarehouse());
        assertEquals("Warehouse Test", warehouseJpaRepository.save(getTestWarehouse1()).getDesignation());
    }

    @Test
    void updateShouldReturnUpdatedWarehouse() {
        when(warehouseJpaRepo.findByIdentifier(1L)).thenReturn(Optional.of(getTestWarehouse()));
        when(warehouseJpaRepo.save(any())).thenReturn(getTestWarehouse());
        assertEquals("Warehouse Test", warehouseJpaRepository.update(getTestWarehouse1()).getDesignation());
    }
}
