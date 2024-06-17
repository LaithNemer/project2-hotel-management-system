package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositry extends JpaRepository<Reservation, Integer> {
}
