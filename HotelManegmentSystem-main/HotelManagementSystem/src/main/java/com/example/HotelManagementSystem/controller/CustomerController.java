package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.CustomerService;
import com.example.HotelManagementSystem.Service.InvoiceService;
import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.ChangePasswordDTO;
import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.dto.InvoiceDto;
import com.example.HotelManagementSystem.dto.RoomDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final RoomService roomService;
    private final InvoiceService invoiceService;

    @Autowired
    public CustomerController(CustomerService customerService, RoomService roomService, InvoiceService invoiceService) {
        this.customerService = customerService;
        this.roomService = roomService;
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Insert a new customer")
    @PostMapping
    public ResponseEntity<CustomerDTO> insertCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO customer1 = customerService.insertCustomer(customer);
        return ResponseEntity.ok(customer1);
    }

    @Operation(summary = "Get all customers")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> array = customerService.getCustomers();
        return ResponseEntity.ok(array);
    }

    @Operation(summary = "Get a customer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id) {
        CustomerDTO customer = customerService.getCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "Update a customer by ID")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customer) {
        CustomerDTO customer1 = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(customer1);
    }

    @Operation(summary = "Delete a customer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable int id) {
        boolean isDeleted = customerService.deleteCustomer(id);
        return ResponseEntity.ok(isDeleted);
    }

    @Operation(summary = "Customer login")
    @PostMapping("/{email}/{password}")
    public ResponseEntity<String> loginCustomer(@PathVariable(name = "email") String email,
                                                @PathVariable(name = "password") String password) {
        String isAuthenticated = customerService.logIn(email, password);
        return ResponseEntity.ok(isAuthenticated);
    }

    @Operation(summary = "Change customer password")
    @PutMapping("/changepassword/{id}")
    public ResponseEntity<CustomerDTO> changePassword(@PathVariable(name = "id") int id,
                                                      @RequestBody ChangePasswordDTO changePasswordDTO) {
        CustomerDTO customerDTO = customerService.changePassword(id, changePasswordDTO);
        return ResponseEntity.ok(customerDTO);
    }

    @Operation(summary = "Get all available rooms")
    @GetMapping("/available-rooms")
    public ResponseEntity<List<RoomDto>> getAllAvailableRooms() {
        List<RoomDto> array = roomService.getAllAvalinleRoom();
        return ResponseEntity.ok(array);
    }

    @Operation(summary = "Insert an invoice for a customer")
    @PostMapping("/insert-invoice/{id}")
    public ResponseEntity<InvoiceDto> insertInvoice(@PathVariable(name = "id") int id) {
        InvoiceDto invoiceDto = invoiceService.insertInvoiceForCustomer(id);
        return ResponseEntity.ok(invoiceDto);
    }
}
