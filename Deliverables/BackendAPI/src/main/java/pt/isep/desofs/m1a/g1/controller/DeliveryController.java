package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pt.isep.desofs.m1a.g1.dto.CreateDeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.exception.NotFoundException;
import pt.isep.desofs.m1a.g1.model.delivery.DeliveryPlan;
import pt.isep.desofs.m1a.g1.service.PdfService;
import pt.isep.desofs.m1a.g1.service.impl.DeliveryPlanServiceImpl;
import pt.isep.desofs.m1a.g1.service.impl.DeliveryServiceImpl;

import java.io.ByteArrayInputStream;
import java.util.List;

@Tag(name = "Delivery")
@RestController
@RequestMapping("/api/v1/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryServiceImpl deliveryServiceImpl;

    @Autowired
    private DeliveryPlanServiceImpl deliveryPlanService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    public DeliveryController(DeliveryServiceImpl deliveryServiceImpl) {
        this.deliveryServiceImpl = deliveryServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> getAllDeliveries() {
        try {
            List<DeliveryDTO> deliveries = deliveryServiceImpl.findAllDeliveries();
            return ResponseEntity.ok(deliveries);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<DeliveryDTO> getDeliveryById(@PathVariable Long identifier) {
        try {
            DeliveryDTO delivery = deliveryServiceImpl.findDeliveryByDeliveryId(identifier);
            return ResponseEntity.ok(delivery);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DeliveryDTO> createDelivery(@RequestBody CreateDeliveryDTO deliveryDTO) {
        try {
            DeliveryDTO savedDelivery = deliveryServiceImpl.createDelivery(deliveryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDelivery);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{identifier}")
    public ResponseEntity<DeliveryDTO> updateDelivery(@PathVariable Long identifier, @RequestBody DeliveryDTO deliveryDTO) {
        try {
            DeliveryDTO updatedDelivery = deliveryServiceImpl.updateDelivery(identifier, deliveryDTO);
            return ResponseEntity.ok(updatedDelivery);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long identifier) {
        try {
            deliveryServiceImpl.deleteDelivery(identifier);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/delivery-plan")
    public ResponseEntity<DeliveryPlan> getDeliveryPlan(@RequestParam("deliveryId") Long deliveryId, @RequestParam("warehouseId") Long warehouseId) {
        try {
            DeliveryPlan deliveryPlan = deliveryPlanService.getDeliveryPlanByDeliveryIdAndDeliveryWarehouseId(deliveryId, warehouseId);
            if (deliveryPlan != null) {
                return ResponseEntity.ok(deliveryPlan);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/delivery-plan/pdf")
    public ResponseEntity<byte[]> getDeliveryPlanPdf(@RequestParam("deliveryId") Long deliveryId, @RequestParam("warehouseId") Long warehouseId) {
        try {
            DeliveryPlan deliveryPlan = deliveryPlanService.getDeliveryPlanByDeliveryIdAndDeliveryWarehouseId(deliveryId, warehouseId);
            ByteArrayInputStream bis = pdfService.generateDeliveryPlanPdf(deliveryPlan);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=deliveryPlan.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bis.readAllBytes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
