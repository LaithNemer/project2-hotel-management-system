package com.example.HotelManagementSystem.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;
}
