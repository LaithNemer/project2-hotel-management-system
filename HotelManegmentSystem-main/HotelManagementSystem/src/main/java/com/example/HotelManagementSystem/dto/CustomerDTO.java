package com.example.HotelManagementSystem.dto;

//import com.example.HotelManagementSystem.entity.Reservation;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
//    private List<Reservation> reservationid;




}
