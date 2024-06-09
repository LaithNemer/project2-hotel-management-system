package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.CustomerService;
import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

     CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable long id){
        CustomerDTO customer=customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody CustomerDTO customer){
        System.out.println(customer.toString());
        Customer customer1=customerService.updateCustomer(id,customer);
        return ResponseEntity.ok(customer1);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean>deleteCustomer(@PathVariable long id){
        boolean isDelet=customerService.deleteCustomer(id);
        return ResponseEntity.ok(isDelet);


    }

    @PostMapping("{email}/{password}")
    public ResponseEntity<String>LoginCustomer(@PathVariable String email, @PathVariable String password){



        String IsExitORNot=customerService.logIn(email,password);
        return ResponseEntity.ok(IsExitORNot);

    }


    @PutMapping("changepassword/{id}")
    public ResponseEntity<CustomerDTO>changePassword(@PathVariable long id , @RequestBody ChangePasswordDTO changePasswordDTO){

        System.out.println(changePasswordDTO.toString());
        CustomerDTO customerDTO=customerService.changePassword(id,changePasswordDTO);

        return ResponseEntity.ok(customerDTO);
    }





//
//    @PostMapping("/register")
//    public ResponseEntity<CustomerDTO> register(@RequestBody CustomerDTO customerDto) {
//        try {
//            CustomerDTO registeredCustomer = customerService.register(customerDto);
//            return ResponseEntity.ok(registeredCustomer);
//        } catch (BadRequestException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<CustomerDTO> login(@RequestParam String email, @RequestParam String password) {
//        try {
//            CustomerDTO customerDto = customerService.login(email, password);
//            return ResponseEntity.ok(customerDto);
//        } catch (BadRequestException e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CustomerDTO> updateProfile(@PathVariable Long id, @RequestBody CustomerDTO customerDto) {
//        try {
//            CustomerDTO updatedCustomer = customerService.updateProfile(id, customerDto);
//            return ResponseEntity.ok(updatedCustomer);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PutMapping("/{id}/change-password")
//    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestParam String newPassword) {
//        try {
//            customerService.changePassword(id, newPassword);
//            return ResponseEntity.ok().build();
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
//        try {
//            CustomerDTO customerDto = customerService.getCustomerById(id);
//            return ResponseEntity.ok(customerDto);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
//        List<CustomerDTO> customers = customerService.getAllCustomers();
//        return ResponseEntity.ok(customers);
//    }
}