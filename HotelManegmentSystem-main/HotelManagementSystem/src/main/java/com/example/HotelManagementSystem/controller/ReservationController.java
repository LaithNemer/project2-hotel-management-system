package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.ReservationService;
import com.example.HotelManagementSystem.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/reservation")
@Tag(name = "Reservation Controller", description = "APIs related to hotel reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @Operation(summary = "Insert a new reservation")
    @PostMapping
    public ResponseEntity<Boolean> insertReservation(@RequestBody ReservationDto reservationDto){
        System.out.println(reservationDto.toString());
        boolean result = reservationService.insertReservation(reservationDto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get all reservations")
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations(){
        List<ReservationDto> array = reservationService.getAllReservation();
        return ResponseEntity.ok(array);
    }

    @Operation(summary = "Get reservations by customer ID and start date")
    @GetMapping("/{id}/{startDate}")
    public ResponseEntity<List<ReservationDto>> getByCustomerIdAndDate(
            @Parameter(description = "Customer ID") @PathVariable int id,
            @Parameter(description = "Start date (yyyy-MM-dd)") @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        System.out.println(id + " " + startDate);
        List<ReservationDto> reservationDto = reservationService.getReservationByIdAndDate(id, startDate.toString());
        return ResponseEntity.ok(reservationDto);
    }

    @Operation(summary = "Get reservations by username and start date")
    @GetMapping("/getReservationUsingname/{username}/{startDate}")
    public ResponseEntity<List<ReservationDto>> getAllReservationByUserNameAndDate(
            @Parameter(description = "Username") @PathVariable(name = "username") String username,
            @Parameter(description = "Start date (yyyy-MM-dd)") @PathVariable(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate){
        List<ReservationDto> reservation = reservationService.getAllResetvationByUserName(username, startDate.toString());
        return ResponseEntity.ok(reservation);
    }

    @Operation(summary = "Delete a reservation by ID")
    @DeleteMapping("{id}")
    public ResponseEntity<ReservationDto> deleteReservation(
            @Parameter(description = "Reservation ID") @PathVariable(name = "id") int id){
        ReservationDto reservationDto = reservationService.deleteReservation(id);
        return ResponseEntity.ok(reservationDto);
    }

    @Operation(summary = "Delete a reservation for a specific room")
    @DeleteMapping("{id}/{roomnumber}")
    public ResponseEntity<ReservationDto> deleteReservationForOneRoom(
            @Parameter(description = "Reservation ID") @PathVariable(name = "id") int id,
            @Parameter(description = "Room number") @PathVariable(name = "roomnumber") int roomnumber){
        ReservationDto reservationDto = reservationService.deleteReservationForOneRoom(id, roomnumber);
        return ResponseEntity.ok(reservationDto);
    }
}
