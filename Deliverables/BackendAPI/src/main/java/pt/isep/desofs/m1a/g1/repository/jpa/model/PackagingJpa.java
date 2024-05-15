package pt.isep.desofs.m1a.g1.repository.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class PackagingJpa {

    @Id
    @GeneratedValue
    private UUID id;
    private String packagingId;
    private long deliveryId;
    private long truckId;
    private String loadTime;
    private String unloadTime;
    private int x;
    private int y;
    private int z;

public PackagingJpa() {
        this.id = UUID.randomUUID();
    }
}
