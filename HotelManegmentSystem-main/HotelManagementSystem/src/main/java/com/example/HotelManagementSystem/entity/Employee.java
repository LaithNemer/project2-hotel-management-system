package com.example.HotelManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;



@Table(name = "employee")
@Entity
@Data

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
    private String name;
    private int age;
    private double salary;
    private String email;
    private String phone;
    private String password;
    private String role;
}
