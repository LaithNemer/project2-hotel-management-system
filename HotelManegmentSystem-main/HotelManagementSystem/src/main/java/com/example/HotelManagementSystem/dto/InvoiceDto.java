package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceDto {

    private int id;
    private int reservationId;
    private double totalprice;
    private int numberofroom;
    private String status;
}
