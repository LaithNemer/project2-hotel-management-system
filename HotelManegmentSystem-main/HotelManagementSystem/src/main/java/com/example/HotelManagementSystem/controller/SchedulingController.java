package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.SchedulingService;
import com.example.HotelManagementSystem.dto.SchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/scheduling")
@Tag(name = "Scheduling Controller", description = "APIs related to scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    @Autowired
    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @Operation(summary = "Add a new scheduling")
    @PostMapping
    public ResponseEntity<SchedulingDTO> addScheduling(@RequestBody SchedulingDTO scheduling) {
        SchedulingDTO schedulingDTO = schedulingService.insertScheduling(scheduling);
        return ResponseEntity.ok(schedulingDTO);
    }

    @Operation(summary = "Get all scheduling for an admin")
    @GetMapping("/admin/{idadmin}")
    public ResponseEntity<List<SchedulingDTO>> getAllScheduling(@Parameter(description = "Admin ID") @PathVariable(name = "idadmin") int idadmin) {
        List<SchedulingDTO> array = schedulingService.getAllScheduling(idadmin);
        return ResponseEntity.ok(array);
    }

    @Operation(summary = "Get scheduling by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDTO> getScheduling(@Parameter(description = "Scheduling ID") @PathVariable(name = "id") int id) {
        SchedulingDTO schedulingDTO = schedulingService.getScheduling(id);
        return ResponseEntity.ok(schedulingDTO);
    }

    @Operation(summary = "Delete scheduling by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<SchedulingDTO> deleteScheduling(@Parameter(description = "Scheduling ID") @PathVariable(name = "id") int id) {
        SchedulingDTO schedulingDTO = schedulingService.deleteScheduling(id);
        return ResponseEntity.ok(schedulingDTO);
    }

    @Operation(summary = "Update scheduling by ID")
    @PutMapping("/{id}")
    public ResponseEntity<SchedulingDTO> updateScheduling(
            @Parameter(description = "Scheduling ID") @PathVariable(name = "id") int id,
            @RequestBody SchedulingDTO schedulingDTO) {
        SchedulingDTO schedulingDTO1 = schedulingService.updateScheduling(id, schedulingDTO);
        return ResponseEntity.ok(schedulingDTO1);
    }
}
