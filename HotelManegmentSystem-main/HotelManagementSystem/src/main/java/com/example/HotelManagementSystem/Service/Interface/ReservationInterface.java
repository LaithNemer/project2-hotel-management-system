package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.ReservationDto;
import com.example.HotelManagementSystem.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationInterface {
    boolean insertReservation(ReservationDto reservationDto);

    List<ReservationDto> getAllReservation();

    List<ReservationDto> getReservationByIdAndDate(int id, String startDate);

    List<ReservationDto> getAllResetvationByUserName(String username, String startdate);

    ReservationDto deleteReservation(int id);

    ReservationDto deleteReservationForOneRoom(int id, int roomnumber);

    List<ReservationDto> getAllApprovmentReservation();

    ReservationDto insertCheckInAndCheckOut(ReservationDto reservationDto);
}
