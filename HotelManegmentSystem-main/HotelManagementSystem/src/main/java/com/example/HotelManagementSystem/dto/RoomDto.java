package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class RoomDto {

    private Long id;
    private String roomNumber;
    private String status;
    private Long reservationId;
    private Long taskId;
}
