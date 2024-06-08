package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.DeliveryDTO;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;
import java.util.Map;

@Tag(name = "Logistics")
@RestController
@RequestMapping(path = "/api/v1/logistics")
public class LogisticsController {

    private static final Logger logger = LoggerFactory.getLogger(LogisticsController.class);

    @Autowired
    private LogisticsService logisticsService;

    @PostMapping("/")
    public ResponseEntity<PackagingDto> submitForm(@Valid  @RequestBody SubmitLogisticsForm request, Authentication authentication){
        logger.info("Received request to submit logistics form from user {}", authentication.getName());
        logisticsService.submitForm(request);
        try {
            logger.info("Received request to submit logistics form from user {}", authentication.getName());
            PackagingDto packagingDto = logisticsService.submitForm(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(packagingDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Get all the packaging
    @GetMapping("/")
    public ResponseEntity<List<Packaging>> getAllPackaging(Authentication authentication) {
        logger.info("Received request to get all packaging from user {}", authentication.getName());
        return ResponseEntity.ok(logisticsService.getAllPackaging());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PackagingDto>> getPackaging(
        @RequestParam(defaultValue = "0") int pageIndex,
        @RequestParam(defaultValue = "5") int pageSize,
        @RequestParam(defaultValue = "packagingId") String sortBy,
        @RequestParam(defaultValue = "asc") String sortOrder,
        @RequestParam(required = false) Map<String, String> filters){
        try {
            List<PackagingDto> packagings = logisticsService.getAllPackaging(pageIndex, pageSize, sortBy, sortOrder, filters);
            return ResponseEntity.ok(packagings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Get the packaging by id
    @GetMapping("/{id}")
    public ResponseEntity<Packaging> getPackagingById(@Valid @PathVariable String id, Authentication authentication) {
        logger.info("Received request to get packaging with id {} from user {}", id, authentication.getName());
        return ResponseEntity.ok(logisticsService.getPackagingById(id));
    }

}
