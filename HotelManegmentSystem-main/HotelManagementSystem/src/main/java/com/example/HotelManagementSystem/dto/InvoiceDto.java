package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceDto {

    private int id;
    private int reservationId;
    private BigDecimal amount;
    private Date issuedDate;
    private Date dueDate;
    private String status;
}
