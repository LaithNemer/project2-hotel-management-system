package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.SchedulingService;
import com.example.HotelManagementSystem.dto.SchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduling")

public class SchedulingController {

    private SchedulingService schedulingService;
    @Autowired
    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    public ResponseEntity<SchedulingDTO> addScheduling(@RequestBody SchedulingDTO scheduling) {
        System.out.println(scheduling.toString());
        SchedulingDTO schedulingDTO=schedulingService.insertScheduling(scheduling);

        return ResponseEntity.ok(schedulingDTO);

    }

    @GetMapping("admin/{idadmin}")
    public ResponseEntity<List<SchedulingDTO>> getAllScheduling(@PathVariable(name = "idadmin") int idadmin) {
        List<SchedulingDTO>array=schedulingService.getAllScheduling(idadmin);
        return ResponseEntity.ok(array);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDTO> getScheduling(@PathVariable(name = "id") int id) {
        SchedulingDTO schedulingDTO=schedulingService.getScheduling(id);
        return ResponseEntity.ok(schedulingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SchedulingDTO>deleteScheduling(@PathVariable(name = "id") int id )
    {

        SchedulingDTO schedulingDTO=schedulingService.deleteScheduling(id);

        return  ResponseEntity.ok(schedulingDTO);

    }



    @PutMapping("/{id}")
    public ResponseEntity<SchedulingDTO>updateScheduling(@PathVariable(name = "id") int id,@RequestBody SchedulingDTO schedulingDTO){
        SchedulingDTO schedulingDTO1=schedulingService.updateScheduling(id,schedulingDTO);
        return  ResponseEntity.ok(schedulingDTO1);

    }
}
