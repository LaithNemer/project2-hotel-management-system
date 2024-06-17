package com.example.HotelManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomNumber;

    private String status;
    private String type;
    private int capacity;
    private int price;
    private String description;
    private String availability;
    private int size;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "room")
    private Task task;

    @ManyToOne
    private Admine admine;


    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", availability='" + availability + '\'' +
                ", size=" + size +
                '}';
    }
}
