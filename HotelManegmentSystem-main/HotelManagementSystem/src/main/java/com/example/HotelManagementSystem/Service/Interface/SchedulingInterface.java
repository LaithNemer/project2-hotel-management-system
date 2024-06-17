package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.SchedulingDTO;

import java.util.List;

public interface SchedulingInterface {
//    SchedulingDTO insertScheduling(SchedulingDTO scheduling);

    List<SchedulingDTO> getAllScheduling(int idadmine);

    SchedulingDTO getScheduling(int id);

    SchedulingDTO deleteScheduling(int id);

    SchedulingDTO updateScheduling(int id ,SchedulingDTO schedulingDTO);

    SchedulingDTO insertScheduling(SchedulingDTO scheduling);
}
