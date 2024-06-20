package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;

import java.util.List;

public interface CustomerInterface {
    CustomerDTO insertCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomer(int id);
    CustomerDTO updateCustomer(int id, CustomerDTO custmr);
    boolean deleteCustomer(int id);
    String logIn(String email,String password);
    CustomerDTO changePassword(int id, ChangePasswordDTO changePasswordDTO);
}
