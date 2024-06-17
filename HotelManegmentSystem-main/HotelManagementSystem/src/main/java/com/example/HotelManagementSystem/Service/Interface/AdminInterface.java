package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.AdminDTO;

import java.util.List;

public interface AdminInterface {
AdminDTO addAdmine(AdminDTO adminDTO);
List<AdminDTO> getAdllEmployee();
AdminDTO getAdmin(int id);

    AdminDTO updateAdmine(int id, AdminDTO admin);

    String logIn(String email, String password);
}
