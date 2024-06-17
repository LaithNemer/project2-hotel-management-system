package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class RoomDto {
    private int roomNumber;
    private String status;
    private String type;
    private int capacity;
    private int price;
    private String description;
    private String availability;
    private int size;
    private int reservationId;
    private int taskId;

    private int adminid;

}
