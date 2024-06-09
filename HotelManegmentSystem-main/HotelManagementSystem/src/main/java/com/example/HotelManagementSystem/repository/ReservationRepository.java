package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    List<Reservation> findByCustomerId(Long customerId);
//    List<Reservation> findByRoomId(Long roomId);
//    List<Reservation> findByStatus(String status);
//    List<Reservation> findByCheckInDateBetween(Date startDate, Date endDate);
}
