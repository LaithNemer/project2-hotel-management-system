package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private int  id;
    private String name;
    private int age;
    private double salary;
    private String email;
    private String phone;
    private String password;
    private String role;
}
