package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.*;
import lombok.Data;
import pt.isep.desofs.m1a.g1.model.delivery.Delivery;

import java.util.UUID;

@Data
@Entity
public class PackagingJpa {

    @Id
    @GeneratedValue
    private UUID id;
    private String packagingId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id")
    private DeliveryJpa delivery;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "truck_id")
    private TruckJpa truck;
    private String loadTime;
    private String unloadTime;
    private int x;
    private int y;
    private int z;

public PackagingJpa() {
        this.id = UUID.randomUUID();
    }
}
