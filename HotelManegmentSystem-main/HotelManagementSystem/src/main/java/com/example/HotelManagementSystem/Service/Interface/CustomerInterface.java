package com.example.HotelManagementSystem.Service.Interface;

import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;

import java.util.List;

public interface CustomerInterface {
    Customer insertCustomer(CustomerDTO customerDTO);
    List<Customer> getCustomers();
    CustomerDTO getCustomer(int id);
    Customer updateCustomer(int id, CustomerDTO custmr);
    boolean deleteCustomer(int id);
    String logIn(String email,String password);
    CustomerDTO changePassword(int id, ChangePasswordDTO changePasswordDTO);
}
