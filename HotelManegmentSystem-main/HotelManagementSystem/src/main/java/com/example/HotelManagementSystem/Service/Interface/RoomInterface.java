package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.RoomDto;

import java.util.List;

public interface RoomInterface {
    RoomDto insertRoom(RoomDto room);

    List<RoomDto> getAllRoom(int id);

    RoomDto ViewRoom(int id, int roomnumber);


    RoomDto deleteRoom(int id, int roomnumber);

    List<RoomDto> getAvailbleRoom(int id);

    List<RoomDto> getAllAvalinleRoom();

    List<RoomDto> getRequestReservation(int id);

    RoomDto updaetOnRequestReservation(RoomDto roomDto);
}
