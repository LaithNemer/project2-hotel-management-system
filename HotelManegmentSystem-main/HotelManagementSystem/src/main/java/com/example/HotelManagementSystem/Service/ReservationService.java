package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.ReservationInterface;
import com.example.HotelManagementSystem.dto.ReservationDto;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.entity.Reservation;
import com.example.HotelManagementSystem.entity.Room;
import com.example.HotelManagementSystem.repository.CustomerRepository;
import com.example.HotelManagementSystem.repository.ReservationRepository;
import com.example.HotelManagementSystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService implements ReservationInterface {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public boolean insertReservation(ReservationDto reservationDto) {
        Customer customer = customerRepository.getById(reservationDto.getCustomerId());

        // Create the reservation object
        Reservation reservation = new Reservation();
        reservation.setStatus("Reservation request");
        reservation.setCustomer(customer);
        reservation.setCheckInDate(reservationDto.getCheckInDate());
        reservation.setCheckOutDate(reservationDto.getCheckOutDate());

        // Save the reservation first to generate its ID
        reservation = reservationRepository.save(reservation);


        List<Room> rooms = new ArrayList<>();
        for (int roomNumber : reservationDto.getRoomnumber()) {
            Room room = roomRepository.getById(roomNumber);
            room.setReservation(reservation);  // Set the reservation for each room
            rooms.add(roomRepository.save(room));  // Save each room after setting the reservation
        }


        reservation.setRooms(rooms);
        reservationRepository.save(reservation);

        return true;
    }
}
