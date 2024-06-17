package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdminDTO {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private double salary;
    private int age;

    private List<Integer> roomid;


}
