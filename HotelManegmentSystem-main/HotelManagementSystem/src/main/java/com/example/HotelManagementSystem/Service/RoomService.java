package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.RoomInterface;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.RoomDto;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.entity.Reservation;
import com.example.HotelManagementSystem.entity.Room;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
import com.example.HotelManagementSystem.repository.ReservationRepositry;
import com.example.HotelManagementSystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements RoomInterface {

    private RoomRepository roomRepository;
    private AdmineRepositry admineRepositry;
    private ReservationRepositry reservationRepositry;


    @Autowired
    public RoomService(RoomRepository roomRepository , AdmineRepositry admineRepositry,ReservationRepositry reservationRepositry) {
        this.roomRepository = roomRepository;
        this.admineRepositry=admineRepositry;
        this.reservationRepositry = reservationRepositry;

    }

    @Override
    public RoomDto insertRoom(RoomDto room) {

        Admine admine=admineRepositry.getById(room.getAdminid());
        Room room1=new Room();
       room1.setRoomNumber(room.getRoomNumber());
       room1.setStatus(room.getStatus());
       room1.setType(room.getType());
       room1.setCapacity(room.getCapacity());
       room1.setPrice(room.getPrice());
       room1.setAvailability(room.getAvailability());
       room1.setSize(room.getSize());
        room1.setDescription(room.getDescription());
        room1.setAdmine(admine);
        roomRepository.save(room1);
        room.setRoomNumber(room1.getRoomNumber());
        return  room    ;



    }

    @Override
    public List<RoomDto> getAllRoom(int id) {
        List<Room>rooms=roomRepository.findAll();
        List<RoomDto> roomDtos=new ArrayList<>();
        for(int i=0;i<rooms.size();i++){

            RoomDto roomDto=new RoomDto();
            if(rooms.get(i).getAdmine().getId()==id){
                roomDto.setRoomNumber(rooms.get(i).getRoomNumber());
                roomDto.setStatus(rooms.get(i).getStatus());
                roomDto.setType(rooms.get(i).getType());
                roomDto.setCapacity(rooms.get(i).getCapacity());
                roomDto.setPrice(rooms.get(i).getPrice());
                roomDto.setAvailability(rooms.get(i).getAvailability());
                roomDto.setSize(rooms.get(i).getSize());
                roomDto.setDescription(rooms.get(i).getDescription());
                roomDto.setAdminid(id);
                roomDto.setReservationId(rooms.get(i).getReservation().getId());
                roomDtos.add(roomDto);

            }
        }
        return roomDtos;
    }

    @Override
    public RoomDto ViewRoom(int id, int roomnumber) {

        List<RoomDto>roomDtos=getAllRoom(id);
        RoomDto roomDto=new RoomDto();
        int flags=-1;
        for(int i=0;i<roomDtos.size();i++){
            if(roomDtos.get(i).getRoomNumber()==roomnumber){
                roomDto=roomDtos.get(i);
                flags=i;
            }
        }

        if(flags==-1){
            throw new BadRequestException("Room"," because the room number is not exist");
        }


        return roomDto;
    }

    @Override
    public RoomDto deleteRoom(int id, int roomnumber) {
        RoomDto roomDto=ViewRoom(id, roomnumber);
        roomRepository.deleteById(roomnumber);

        return roomDto;
    }

    @Override
    public List<RoomDto> getAvailbleRoom(int id) {
        List<RoomDto>array=getAllRoom(id);
        List<RoomDto>roomDtos=new ArrayList<>();

        for(int i=0;i<array.size();i++){
            if(array.get(i).getStatus().toLowerCase().equals("available")){

                roomDtos.add(array.get(i));
            }
        }


        return roomDtos;

    }

    @Override
    public List<RoomDto> getAllAvalinleRoom() {
        List<Room>rooms=roomRepository.findByStatus("available");
        List<RoomDto>roomDtos=new ArrayList<>();
        for(int i=0;i<rooms.size();i++){
            RoomDto roomDto=new RoomDto();

            roomDto.setRoomNumber(rooms.get(i).getRoomNumber());
            roomDto.setStatus(rooms.get(i).getStatus());
            roomDto.setType(rooms.get(i).getType());
            roomDto.setCapacity(rooms.get(i).getCapacity());
            roomDto.setPrice(rooms.get(i).getPrice());
            roomDto.setAvailability(rooms.get(i).getAvailability());
            roomDto.setSize(rooms.get(i).getSize());
            roomDto.setDescription(rooms.get(i).getDescription());
            roomDto.setAdminid(rooms.get(i).getAdmine().getId());
            roomDtos.add(roomDto);

        }

        return roomDtos;
    }

    @Override
    public List<RoomDto> getRequestReservation(int id) {
        List<Room>rooms=roomRepository.findAll();
        List<RoomDto> roomDtos=new ArrayList<>();
        for(int i=0;i<rooms.size();i++){

            RoomDto roomDto=new RoomDto();
            if(rooms.get(i).getAdmine().getId()==id){
                if(rooms.get(i).getStatus().equals("Reservation request")){
                    roomDto.setRoomNumber(rooms.get(i).getRoomNumber());
                    roomDto.setStatus(rooms.get(i).getStatus());
                    roomDto.setType(rooms.get(i).getType());
                    roomDto.setCapacity(rooms.get(i).getCapacity());
                    roomDto.setPrice(rooms.get(i).getPrice());
                    roomDto.setAvailability(rooms.get(i).getAvailability());
                    roomDto.setSize(rooms.get(i).getSize());
                    roomDto.setDescription(rooms.get(i).getDescription());
                    roomDto.setAdminid(id);
                    roomDto.setReservationId(rooms.get(i).getReservation().getId());
                    roomDtos.add(roomDto);
                }


            }
        }
        return roomDtos;
    }

    @Override
    public RoomDto updaetOnRequestReservation(RoomDto roomDto) {
        Room room=roomRepository.getById(roomDto.getRoomNumber());
        Reservation reservation=reservationRepositry.getById(roomDto.getReservationId());

        if(roomDto.getStatus().equals("rejected")){
            room.setStatus("available");
             room.setReservation(null);;
            roomRepository.save(room);

            reservation.setStatus(roomDto.getStatus());
            reservationRepositry.save(reservation);
        }
        else{
            room.setStatus(roomDto.getStatus());
            Reservation reservation1=reservationRepositry.getById(roomDto.getReservationId());
            room.setReservation(reservation1);
            System.out.println(reservation.toString());
            reservation.setStatus(roomDto.getStatus());
            reservationRepositry.save(reservation);
        }



        return roomDto;
    }



}
