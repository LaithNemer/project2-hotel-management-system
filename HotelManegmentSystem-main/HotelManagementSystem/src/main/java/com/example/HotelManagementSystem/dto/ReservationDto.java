package com.example.HotelManagementSystem.dto;

import com.example.HotelManagementSystem.entity.Room;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {

    private int id;
    private int customerId;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private String arrivalDate;
    private String exitDate;
    private List<Integer>roomnumber;
}