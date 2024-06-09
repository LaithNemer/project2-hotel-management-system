package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.AdminDTO;

import java.util.List;

public interface AdminInterface {
AdminDTO addAdmine(AdminDTO adminDTO);
List<AdminDTO> getAdllEmployee();
AdminDTO getAdmin(int id);

}
