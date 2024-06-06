package pt.isep.desofs.m1a.g1.bean;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitLogisticsForm {

    @NotNull(message = "Packaging ID cannot be null")
    @Size(min = 1, message = "Packaging ID must not be empty")
    private String packagingId;

    @NotNull(message = "Delivery ID cannot be null")
    @Min(value = 1, message = "Delivery ID must be greater than 0")
    private long deliveryId;

    @NotNull(message = "Truck ID cannot be null")
    @Min(value = 1, message = "Truck ID must be greater than 0")
    private long truckId;

    @NotNull(message = "Load time cannot be null")
    @Size(min = 1, message = "Load time must not be empty")
    private String loadTime;

    @NotNull(message = "Unload time cannot be null")
    @Size(min = 1, message = "Unload time must not be empty")
    private String unloadTime;

    @Min(value = 0, message = "X must be greater than or equal to 0")
    private int x;

    @Min(value = 0, message = "Y must be greater than or equal to 0")
    private int y;

    @Min(value = 0, message = "Z must be greater than or equal to 0")
    private int z;
}
