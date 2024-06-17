package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.entity.Employee;

import java.util.List;

public interface EmployeeInterface {
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEMployeeForAdmine(int id);
    EmployeeDto getEmployee(int id,String username);

    EmployeeDto deleteEmployee(int idadmin, String  name);

    EmployeeDto updateEmployee(int id, String username, EmployeeDto employeeDto);

    EmployeeDto deleteEmpolyeeById(int id, String name);
}
