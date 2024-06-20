package com.example.HotelManagementSystem.dto;

import com.example.HotelManagementSystem.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class AdminDTO {
    private int id;
    private double salary;
    private String role;
    private User user;


}
