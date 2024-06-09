package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private double salary;
    private int age;


}
