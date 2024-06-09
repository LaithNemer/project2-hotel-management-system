package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.entity.Employee;

import java.util.List;

public interface EmployeeInterface {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEMployeeForAdmine(int id);
    EmployeeDto getEmployee(int id,String username);
}
