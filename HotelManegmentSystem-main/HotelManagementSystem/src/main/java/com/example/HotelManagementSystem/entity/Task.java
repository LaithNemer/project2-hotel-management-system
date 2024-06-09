package com.example.HotelManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date startDate;
    private Date endDate;
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Scheduling scheduling;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

}
