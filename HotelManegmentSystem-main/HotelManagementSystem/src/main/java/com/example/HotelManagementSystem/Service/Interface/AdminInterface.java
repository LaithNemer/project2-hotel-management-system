package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.entity.User;

import java.util.List;

public interface AdminInterface {
AdminDTO addAdmine(AdminDTO adminDTO);
List<EmployeeDto> getAdllEmployee();
User getAdmin(int id);
//
//    AdminDTO updateAdmine(int id, AdminDTO admin);
//
    String logIn(String email, String password);
//
    EmployeeDto insertEmployee(int idAdmin, AdminDTO adminDTO);
//
    EmployeeDto getOneEmployee(String username);
//
    EmployeeDto deleteEmployee(int id);
//
    EmployeeDto updateEmployee(int id, EmployeeDto employeeDto);

    List<User> getAllAdmine();
}
