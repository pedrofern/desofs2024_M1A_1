package pt.isep.desofs.m1a.g1.repository.jpa.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;
import pt.isep.desofs.m1a.g1.model.warehouse.Warehouse;
import pt.isep.desofs.m1a.g1.repository.jpa.DeliveryJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.WarehouseJpaRepo;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.DeliveryJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.mapper.WarehouseJpaMapper;
import pt.isep.desofs.m1a.g1.repository.jpa.model.DeliveryJpa;
import pt.isep.desofs.m1a.g1.repository.jpa.model.WarehouseJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeliveryJpaRepositoryImplTest {

    @Mock
    private DeliveryJpaRepo deliveryJpaRepo;

    @Mock
    private WarehouseJpaRepo wRepo;

    @InjectMocks
    private DeliveryJpaRepositoryImpl deliveryJpaRepository;

    private static final DeliveryJpaMapper mapper = DeliveryJpaMapper.INSTANCE;
    private static final WarehouseJpaMapper wMapper = WarehouseJpaMapper.INSTANCE;

    private static Warehouse getTestWarehouse() {
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

    private static DeliveryJpa getTestDelivery() {
        return mapper.deliveryToDeliveryJpa(getTestDelivery1());
    }

    private static Delivery getTestDelivery1() {
        return new Delivery(1L, "2024-01-01", 10.0, 1L);
    }

    private static Delivery getTestDelivery2() {
        return new Delivery(2L, "2024-02-02", 20.0, 2L);
    }

    private static List<DeliveryJpa> getTestDeliveries() {
        return Stream.of(getTestDelivery1(), getTestDelivery2())
                .map(mapper::deliveryToDeliveryJpa)
                .toList();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllShouldReturnAllDeliverys() {
        when(deliveryJpaRepo.findAll()).thenReturn(getTestDeliveries());
        assertEquals(2, deliveryJpaRepository.findAll().size());
    }

    @Test
    void findByIdentifierShouldReturnDelivery() {
        when(deliveryJpaRepo.findByDeliveryId(1L)).thenReturn(Optional.of(getTestDelivery()));
        assertEquals("2024-01-01", deliveryJpaRepository.findByDeliveryId(1L).getDeliveryDate());
    }

    @Test
    void saveShouldReturnSavedDelivery() {
        WarehouseJpa w = wMapper.toDatabaseEntity(getTestWarehouse());
        when(wRepo.findByIdentifier(1L)).thenReturn(Optional.of(w));
        when(deliveryJpaRepo.save(any())).thenReturn(getTestDelivery());
        assertEquals("2024-01-01", deliveryJpaRepository.create(getTestDelivery1()).getDeliveryDate());
    }

    @Test
    void updateShouldReturnUpdatedDelivery() {
        WarehouseJpa w = wMapper.toDatabaseEntity(getTestWarehouse());
        when(wRepo.findByIdentifier(1L)).thenReturn(Optional.of(w));
        when(deliveryJpaRepo.findByDeliveryId(1L)).thenReturn(Optional.of(getTestDelivery()));
        when(deliveryJpaRepo.save(any())).thenReturn(getTestDelivery());
        assertEquals("2024-01-01", deliveryJpaRepository.update(getTestDelivery1()).getDeliveryDate());
    }
}
