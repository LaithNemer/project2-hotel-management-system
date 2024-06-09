package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private String type;
    private Long schedulingId;
    private Long roomId;
}
