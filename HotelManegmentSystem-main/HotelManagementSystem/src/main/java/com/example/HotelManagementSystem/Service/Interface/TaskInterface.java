package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.TaskDTO;

import java.util.List;

public interface TaskInterface {
    TaskDTO insertTaskForEmployee(TaskDTO taskDTO);

    TaskDTO getTask(int id);

    TaskDTO deleteTask(int id);

    List<TaskDTO> getAllTask(int id);
}
