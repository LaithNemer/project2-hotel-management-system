package com.example.HotelManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "admin")
@Data
public class Admine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String role;
    private double salary;
    private int age;


    @OneToMany( cascade = {CascadeType.ALL},mappedBy = "admine")
    private List<Room> room;

}
