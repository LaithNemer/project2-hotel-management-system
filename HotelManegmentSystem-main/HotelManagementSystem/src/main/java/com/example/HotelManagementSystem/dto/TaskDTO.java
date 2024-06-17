package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private int id;
    private Date startDate;
    private Date endDate;
    private String type;
    private int schedulingId;
    private int roomnumber;
}
