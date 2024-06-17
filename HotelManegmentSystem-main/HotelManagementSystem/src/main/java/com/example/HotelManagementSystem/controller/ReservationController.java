package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.ReservationService;
import com.example.HotelManagementSystem.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
