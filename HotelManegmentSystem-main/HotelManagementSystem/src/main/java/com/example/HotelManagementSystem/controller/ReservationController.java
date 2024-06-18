package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.ReservationService;
import com.example.HotelManagementSystem.dto.ReservationDto;
import com.example.HotelManagementSystem.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService=reservationService;

    }

    @PostMapping
    public ResponseEntity<Boolean>insertReservation(@RequestBody ReservationDto reservationDto){
        System.out.println(reservationDto.toString());
        boolean reservationDto1=reservationService.insertReservation(reservationDto);
        return  ResponseEntity.ok(reservationDto1);
    }
    @GetMapping
    public ResponseEntity<List<ReservationDto>>getAllReservations(){
        List<ReservationDto> array=reservationService.getAllReservation();
        return ResponseEntity.ok(array);
    }


    @GetMapping("/{id}/{startDate}")
    public ResponseEntity<List<ReservationDto>> getByCustomerIdAndDate(@PathVariable(name = "id") int id, @PathVariable(name = "startDate") String startDate) {
        System.out.println(id+" "+startDate);
        List<ReservationDto> reservationDto=reservationService.getReservationByIdAndDate(id,startDate);
        return ResponseEntity.ok(reservationDto);
    }

    @GetMapping("/getReservationUsingname/{username}/{startDate}")
    public ResponseEntity<List<ReservationDto>> getAllReservationByUserNameAndDate(@PathVariable(name = "username")String username, @PathVariable(name = "startDate")String startdate){
        List<ReservationDto>reservation=reservationService.getAllResetvationByUserName(username,startdate);
        return ResponseEntity.ok(reservation);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<ReservationDto>deleteReservation(@PathVariable(name = "id")int  id){
        ReservationDto reservationDto=reservationService.deleteReservation(id);
        return  ResponseEntity.ok(reservationDto);
    }

    @DeleteMapping("{id}/{roomnumber}")
    public ResponseEntity<ReservationDto>deleetOReservationForOneRoom(@PathVariable(name = "id")int id,@PathVariable(name = "roomnumber")int roomnumber){
        ReservationDto reservationDto=reservationService.deleteReservationForOneRoom(id,roomnumber);
        return  ResponseEntity.ok(reservationDto);
    }

}
