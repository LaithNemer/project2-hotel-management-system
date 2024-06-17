package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class SchedulingDTO {

    private int id;
    private String status;
    private int adminid;
    private String note;
    private String employename;

}
