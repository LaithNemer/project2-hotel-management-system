package com.example.HotelManagementSystem.dto;

import com.example.HotelManagementSystem.entity.Room;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {

    private Long id;
    private Long customerId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private List<Long>rooms;
}