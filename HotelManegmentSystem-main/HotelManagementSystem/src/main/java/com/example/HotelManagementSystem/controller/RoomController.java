package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.RoomDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {


    private RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;

    }

    @PostMapping()
    public ResponseEntity<RoomDto> addRoom(@RequestBody  RoomDto room) {

        RoomDto roomDto=roomService.insertRoom(room);
        return ResponseEntity.ok(roomDto);
    }


    @GetMapping("{id}")
    public ResponseEntity<List<RoomDto>>getAllRoom(@PathVariable(name = "id") int id) {
        List<RoomDto>array=roomService.getAllRoom(id);
        return  ResponseEntity.ok(array);

    }

    @GetMapping("{id}/{roomnumber}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable(name = "id") int id, @PathVariable(name = "roomnumber") int roomnumber) {
        RoomDto roomDto=roomService.ViewRoom(id,roomnumber);
        return ResponseEntity.ok(roomDto);

    }


    @DeleteMapping("{id}/{roomnumber}")
    public ResponseEntity<RoomDto>DeleetRoom(@PathVariable(name = "id") int id, @PathVariable(name = "roomnumber") int roomnumber) {
        RoomDto roomDto=roomService.deleteRoom(id,roomnumber);
        return ResponseEntity.ok(roomDto);

    }





}
