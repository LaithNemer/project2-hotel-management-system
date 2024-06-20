package com.example.HotelManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "admin")
@Data
public class Admine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    private double salary;


    @OneToMany( cascade = {CascadeType.ALL},mappedBy = "admine")
    private List<Room> room;
    @OneToOne
    @JsonIgnore
    private User user;


}
