package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.RoomDto;
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
@RequestMapping("/api/v1/room")
@Tag(name = "Room Controller", description = "APIs related to hotel rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "Add a new room")
    @PostMapping
    public ResponseEntity<RoomDto> addRoom(@RequestBody RoomDto room) {
        RoomDto roomDto = roomService.insertRoom(room);
        return ResponseEntity.ok(roomDto);
    }

    @Operation(summary = "Get all rooms by hotel ID")
    @GetMapping("/{id}")
    public ResponseEntity<List<RoomDto>> getAllRoom(@Parameter(description = "Hotel ID") @PathVariable(name = "id") int id) {
        List<RoomDto> array = roomService.getAllRoom(id);
        return ResponseEntity.ok(array);
    }

    @Operation(summary = "Get a room by hotel ID and room number")
    @GetMapping("/{id}/{roomnumber}")
    public ResponseEntity<RoomDto> getRoom(
            @Parameter(description = "Hotel ID") @PathVariable(name = "id") int id,
            @Parameter(description = "Room number") @PathVariable(name = "roomnumber") int roomnumber) {
        RoomDto roomDto = roomService.ViewRoom(id, roomnumber);
        return ResponseEntity.ok(roomDto);
    }

    @Operation(summary = "Delete a room by hotel ID and room number")
    @DeleteMapping("/{id}/{roomnumber}")
    public ResponseEntity<RoomDto> deleteRoom(
            @Parameter(description = "Hotel ID") @PathVariable(name = "id") int id,
            @Parameter(description = "Room number") @PathVariable(name = "roomnumber") int roomnumber) {
        RoomDto roomDto = roomService.deleteRoom(id, roomnumber);
        return ResponseEntity.ok(roomDto);
    }
}
