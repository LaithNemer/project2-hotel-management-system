package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.CustomerService;
import com.example.HotelManagementSystem.Service.InvoiceService;
import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.dto.InvoiceDto;
import com.example.HotelManagementSystem.dto.RoomDto;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

     private  CustomerService customerService;
     private RoomService roomService;
     private InvoiceService invoiceService;


    @Autowired
    public CustomerController(CustomerService customerService,RoomService roomService,InvoiceService invoiceService) {
        this.customerService = customerService;
        this.roomService=roomService;
        this.invoiceService=invoiceService;

    }





    @PostMapping
    public ResponseEntity<CustomerDTO>insertCustomer(@RequestBody CustomerDTO customer){
        System.out.println(customer.toString());
    CustomerDTO customer1=customerService.insertCustomer(customer);
        return ResponseEntity.ok(customer1);

    }

    
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO>array=customerService.getCustomers();
        return ResponseEntity.ok(array);

    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id){
        CustomerDTO customer=customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id,@RequestBody CustomerDTO customer){
        System.out.println(customer.toString());
        CustomerDTO customer1=customerService.updateCustomer(id,customer);
        return ResponseEntity.ok(customer1);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean>deleteCustomer(@PathVariable int id){
        boolean isDelet=customerService.deleteCustomer(id);
        return ResponseEntity.ok(isDelet);


    }

    @PostMapping("{email}/{password}")
    public ResponseEntity<String>LoginCustomer(@PathVariable(name = "email") String email, @PathVariable(name = "password") String password){
    System.out.println(email);
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

    @PostMapping("/insertInvoice/{id}")
    public ResponseEntity<InvoiceDto>insertInvoce(@PathVariable(name = "id") int id ){
        InvoiceDto invoiceDto=invoiceService.insertInvoiceForCustomer(id);

        return  ResponseEntity.ok(invoiceDto);

    }


}