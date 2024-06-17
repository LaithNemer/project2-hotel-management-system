package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.CustomerService;
import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.dto.RoomDto;
import com.example.HotelManagementSystem.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

     private  CustomerService customerService;
     private RoomService roomService;


    @Autowired
    public CustomerController(CustomerService customerService,RoomService roomService) {
        this.customerService = customerService;
        this.roomService=roomService;

    }





    @PostMapping
    public ResponseEntity<Customer>insertCustomer(@RequestBody CustomerDTO customer){
        System.out.println(customer.toString());
    Customer customer1=customerService.insertCustomer(customer);
        return ResponseEntity.ok(customer1);

    }

    
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer>array=customerService.getCustomers();
        return ResponseEntity.ok(array);

    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id){
        CustomerDTO customer=customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id,@RequestBody CustomerDTO customer){
        System.out.println(customer.toString());
        Customer customer1=customerService.updateCustomer(id,customer);
        return ResponseEntity.ok(customer1);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean>deleteCustomer(@PathVariable int id){
        boolean isDelet=customerService.deleteCustomer(id);
        return ResponseEntity.ok(isDelet);


    }

    @PostMapping("/{email}/{password}")
    public ResponseEntity<String>LoginCustomer(@PathVariable(name = "email") String email, @PathVariable(name = "password") String password){

        String IsExitORNot=customerService.logIn(email,password);
        return ResponseEntity.ok(IsExitORNot);

    }


    @PutMapping("/changepassword/{id}")
    public ResponseEntity<CustomerDTO>changePassword(@PathVariable(name = "id") int id , @RequestBody ChangePasswordDTO changePasswordDTO){

        System.out.println(changePasswordDTO.toString());
        CustomerDTO customerDTO=customerService.changePassword(id,changePasswordDTO);

        return ResponseEntity.ok(customerDTO);
    }



    @GetMapping("/AvailbeRoom")
    public ResponseEntity<List<RoomDto>>getAllAvailbeRoom(){
        List<RoomDto>array=roomService.getAllAvalinleRoom();
        return  ResponseEntity.ok(array);
    }

}