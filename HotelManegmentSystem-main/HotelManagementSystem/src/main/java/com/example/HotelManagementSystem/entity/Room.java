package com.example.HotelManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private String status; // Available, Occupied, Maintenance, etc.
    private String type;


    @ManyToOne(cascade = CascadeType.ALL)
    Reservation reservation;


    @OneToOne(cascade = CascadeType.ALL)
    Task task;


}
