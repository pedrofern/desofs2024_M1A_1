package pt.isep.desofs.m1a.g1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private LogisticsService logisticsService;

    @PostMapping("/")
    public void submitForm(@RequestBody SubmitLogisticsForm request) {
        logisticsService.submitForm(request);
    }

    //Get all the packaging
    @GetMapping("/")
    public ResponseEntity<List<Packaging>> getAllPackaging() {
        return ResponseEntity.ok(logisticsService.getAllPackaging());
    }

    //Get the packaging by id
    @GetMapping("/{id}")
    public ResponseEntity<Packaging> getPackagingById(@PathVariable String id) {
        return ResponseEntity.ok(logisticsService.getPackagingById(id));
    }

}
