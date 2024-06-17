package com.example.HotelManagementSystem.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;
    private String Note;
    private String employeename;



    @ManyToOne(cascade = CascadeType.ALL)
    private Admine admine;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "scheduling")
    private List<Task> tasks;
}
