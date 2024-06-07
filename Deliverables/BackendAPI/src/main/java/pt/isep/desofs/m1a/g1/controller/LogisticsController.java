package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pt.isep.desofs.m1a.g1.bean.SubmitLogisticsForm;
import pt.isep.desofs.m1a.g1.dto.PackagingDto;
import pt.isep.desofs.m1a.g1.model.logistics.Packaging;
import pt.isep.desofs.m1a.g1.service.LogisticsService;

import java.util.List;

@Tag(name = "Logistics")
@RestController
@RequestMapping(path = "/api/v1/logistics")
public class LogisticsController {

    private static final Logger logger = LoggerFactory.getLogger(LogisticsController.class);

    @Autowired
    private LogisticsService logisticsService;

    @PostMapping
    public void submitForm(@Valid  @RequestBody SubmitLogisticsForm request, Authentication authentication){
        logger.info("Received request to submit logistics form from user {}", authentication.getName());
        logisticsService.submitForm(request);
    }

    //Get all the packaging
    @GetMapping
    public ResponseEntity<List<Packaging>> getAllPackaging(Authentication authentication) {
        logger.info("Received request to get all packaging from user {}", authentication.getName());
        return ResponseEntity.ok(logisticsService.getAllPackaging());
    }

    //Get the packaging by id
    @GetMapping("/{id}")
    public ResponseEntity<Packaging> getPackagingById(@Valid @PathVariable String id, Authentication authentication) {
        logger.info("Received request to get packaging with id {} from user {}", id, authentication.getName());
        return ResponseEntity.ok(logisticsService.getPackagingById(id));
    }

}
