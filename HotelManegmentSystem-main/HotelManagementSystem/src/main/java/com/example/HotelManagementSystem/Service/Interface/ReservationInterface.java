package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.ReservationDto;

public interface ReservationInterface {
    boolean insertReservation(ReservationDto reservationDto);
}
