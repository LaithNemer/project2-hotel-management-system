package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.ReservationInterface;
import com.example.HotelManagementSystem.dto.ReservationDto;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.entity.Reservation;
import com.example.HotelManagementSystem.entity.Room;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.CustomerRepository;
import com.example.HotelManagementSystem.repository.ReservationRepository;
import com.example.HotelManagementSystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        Reservation reservation = new Reservation();
        reservation.setStatus("Reservation request");
        reservation.setCustomer(customer);
        reservation.setCheckInDate(reservationDto.getCheckInDate());
        reservation.setCheckOutDate(reservationDto.getCheckOutDate());

        reservation = reservationRepository.save(reservation);


        List<Room> rooms = new ArrayList<>();
        for (int roomNumber : reservationDto.getRoomnumber()) {
            Room room = roomRepository.getById(roomNumber);
            if(room.getStatus().equals("approve")|| room.getStatus().equals("Reservation request")) {
                throw new BadRequestException("Room","because room is  not avilabel");
            }
            room.setStatus("Reservation request");
            room.setReservation(reservation);
            rooms.add(roomRepository.save(room));
        }


        reservation.setRooms(rooms);
        reservationRepository.save(reservation);

        return true;
    }

    @Override
    public List<ReservationDto> getAllReservation() {

        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationDto dto = new ReservationDto();
            dto.setId(reservation.getId());
            dto.setCustomerId(reservation.getCustomer().getId());
            dto.setCheckInDate(reservation.getCheckInDate());
            dto.setCheckOutDate(reservation.getCheckOutDate());
            dto.setStatus(reservation.getStatus());
            List<Integer>temp=new ArrayList<>();

            for(int j=0;j<reservation.getRooms().size();j++){
                Room room = reservation.getRooms().get(j);
                temp.add(room.getRoomNumber());

            }
            dto.setRoomnumber(temp);
            reservationDtos.add(dto);

        }

        return reservationDtos;


    }

    @Override
    public List<ReservationDto> getReservationByIdAndDate(int id, String startDate) {
        List<Reservation>reservations=reservationRepository.findAll();
        List<ReservationDto>array=new ArrayList<>();
        for(int i=0;i<reservations.size();i++){
            ReservationDto reservationDto=new ReservationDto();
            if(id==reservations.get(i).getCustomer().getId()&& startDate.equals(reservations.get(i).getCheckInDate())){
                reservationDto.setId(reservations.get(i).getId());
                reservationDto.setCustomerId(reservations.get(i).getCustomer().getId());
                reservationDto.setCheckInDate(reservations.get(i).getCheckInDate());
                reservationDto.setCheckOutDate(reservations.get(i).getCheckOutDate());
                List<Room>rooms =roomRepository.findRoomsByReservationId(reservations.get(i).getId());
                List<Integer>saveRoomNumber=new ArrayList<>();
                for(int j=0;j<rooms.size();j++){
                    saveRoomNumber.add(rooms.get(j).getRoomNumber());

                }

                reservationDto.setRoomnumber(saveRoomNumber);

                reservationDto.setStatus(reservations.get(i).getStatus());
                array.add(reservationDto);
            }
        }

        return array;




    }

    @Override
    public List<ReservationDto> getAllResetvationByUserName(String username, String startdate) {

        List<Reservation>reservations=reservationRepository.findAll();
        List<ReservationDto>array=new ArrayList<>();
        for(int i=0;i<reservations.size();i++){
            Customer customer=customerRepository.getById(reservations.get(i).getCustomer().getId());
            ReservationDto reservationDto=new ReservationDto();
            if(customer.getUser().getUsername().equals(username)&& reservations.get(i).getCheckInDate().equals(startdate)){

                reservationDto.setId(reservations.get(i).getId());
                reservationDto.setCustomerId(reservations.get(i).getCustomer().getId());
                reservationDto.setCheckInDate(reservations.get(i).getCheckInDate());
                reservationDto.setCheckOutDate(reservations.get(i).getCheckOutDate());
                List<Room>rooms =roomRepository.findRoomsByReservationId(reservations.get(i).getId());
                List<Integer>saveRoomNumber=new ArrayList<>();
                for(int j=0;j<rooms.size();j++){
                    saveRoomNumber.add(rooms.get(j).getRoomNumber());

                }

                reservationDto.setRoomnumber(saveRoomNumber);

                reservationDto.setStatus(reservations.get(i).getStatus());
                array.add(reservationDto);
            }

        }
        return array;
    }

    @Override
    public ReservationDto deleteReservation(int id) {
        Reservation reservation=reservationRepository.getById(id);
        ReservationDto reservationDto=new ReservationDto();
        reservationDto.setCustomerId(reservation.getCustomer().getId());
        reservationDto.setStatus(reservation.getStatus());
        reservationDto.setCheckInDate(reservation.getCheckInDate());
        reservationDto.setCheckOutDate(reservation.getCheckOutDate());

        List<Integer>roomsnumber=new ArrayList<>();
        for(int i=0;i<reservation.getRooms().size();i++){
            Room room=roomRepository.getById(reservation.getRooms().get(i).getRoomNumber());
            room.setStatus("available");
            room.setReservation(null);
            roomRepository.save(room);

            roomsnumber.add(reservation.getRooms().get(i).getRoomNumber());
        }
        reservationDto.setRoomnumber(roomsnumber);
        reservationRepository.deleteById(id);

        return reservationDto;
    }

    @Override
    public ReservationDto deleteReservationForOneRoom(int id, int roomnumber) {
        Reservation reservation=reservationRepository.getById(id);
        ReservationDto reservationDto=new ReservationDto();
        reservationDto.setCustomerId(reservation.getCustomer().getId());
        reservationDto.setStatus("deleted");
        reservationDto.setCheckInDate(reservation.getCheckInDate());
        reservationDto.setCheckOutDate(reservation.getCheckOutDate());
        reservationDto.setId(id);
        Room room=roomRepository.getById(roomnumber);
        room.setStatus("available");
        room.setReservation(null);
        roomRepository.save(room);
        List<Integer>temp=new ArrayList<>(roomnumber);


        reservationDto.setRoomnumber(temp);
        reservationRepository.deleteById(id);

        return reservationDto;
    }

    @Override
    public List<ReservationDto> getAllApprovmentReservation() {
        List<Reservation>reservations=reservationRepository.findAll();
        List<ReservationDto>array=new ArrayList<>();
        for(int i=0;i<reservations.size();i++){
            if(reservations.get(i).getStatus().toLowerCase().equals("approve")){
                ReservationDto reservationDto=new ReservationDto();
                reservationDto.setId(reservations.get(i).getId());
                reservationDto.setCustomerId(reservations.get(i).getId());
                reservationDto.setCheckInDate(reservations.get(i).getCheckInDate());
                reservationDto.setCheckOutDate(reservations.get(i).getCheckOutDate());
                reservationDto.setStatus("approve");
                reservationDto.setArrivalDate(reservations.get(i).getArrivalDate());
                reservationDto.setExitDate(reservations.get(i).getExitDate());
                List<Integer>roomnumber=new ArrayList<>();
                for(int j=0;j<reservations.get(i).getRooms().size();j++){
                    roomnumber.add(reservations.get(i).getRooms().get(j).getRoomNumber());
                }
               reservationDto.setRoomnumber(roomnumber);
                array.add(reservationDto);


            }
        }

        return array;
    }

    @Override
    public ReservationDto insertCheckInAndCheckOut(ReservationDto reservationDto) {
        Reservation reservation=reservationRepository.getById(reservationDto.getId());
        reservationDto.setStatus(reservation.getStatus());
        reservationDto.setCustomerId(reservation.getCustomer().getId());
        reservationDto.setCheckOutDate(reservation.getCheckOutDate());
        reservationDto.setCheckInDate(reservation.getCheckInDate());
        List<Integer>numberRoom=new ArrayList<>();
        for (int i =0;i<reservation.getRooms().size();i++){
            numberRoom.add(reservation.getRooms().get(i).getRoomNumber());

        }
        reservationDto.setRoomnumber(numberRoom);
        reservation.setArrivalDate(reservationDto.getArrivalDate());
        reservation.setExitDate(reservationDto.getExitDate());
        reservationRepository.save(reservation);
        return reservationDto;
    }


}
