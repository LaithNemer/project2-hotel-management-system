package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.AdmineService;
import com.example.HotelManagementSystem.Service.InvoiceService;
import com.example.HotelManagementSystem.Service.ReservationService;
import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.dto.InvoiceDto;
import com.example.HotelManagementSystem.dto.ReservationDto;
import com.example.HotelManagementSystem.dto.RoomDto;
import com.example.HotelManagementSystem.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@Tag(name = "Admin Controller", description = "APIs for managing admin functionalities")
public class AdmineController {

    private final AdmineService admineService;
    private final InvoiceService invoiceService;
    private final RoomService roomService;
    private final ReservationService reservationService;

    @Autowired
    public AdmineController(AdmineService admineService, RoomService roomService, ReservationService reservationService, InvoiceService invoiceService) {
        this.admineService = admineService;
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.invoiceService = invoiceService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new admin")
    public ResponseEntity<AdminDTO> insertAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO registeredAdmin = admineService.addAdmine(adminDTO);
        return ResponseEntity.ok(registeredAdmin);
    }

    @GetMapping
    @Operation(summary = "Get all admins")
    public ResponseEntity<List<User>> getAllAdmins() {
        List<User> admins = admineService.getAllAdmine();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get admin by ID")
    public ResponseEntity<User> getAdminById(@PathVariable int id) {
        User admin = admineService.getAdmin(id);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/{id}/employees")
    @Operation(summary = "Insert an employee under a specific admin")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody AdminDTO adminDTO, @PathVariable int id) {
        EmployeeDto newEmployee = admineService.insertEmployee(id, adminDTO);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping("/employees/{username}")
    @Operation(summary = "Get an employee by username")
    public ResponseEntity<EmployeeDto> getEmployeeByUsername(@PathVariable String username) {
        EmployeeDto employee = admineService.getOneEmployee(username);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/employees")
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = admineService.getAdllEmployee();
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Delete an employee by ID")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable int id) {
        EmployeeDto deletedEmployee = admineService.deleteEmployee(id);
        return ResponseEntity.ok(deletedEmployee);
    }

    @PutMapping("/employees/{id}")
    @Operation(summary = "Update an employee by ID")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = admineService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/{id}/reservation-requests")
    @Operation(summary = "View reservation requests by admin ID")
    public ResponseEntity<List<RoomDto>> viewReservationRequests(@PathVariable int id) {
        List<RoomDto> requests = roomService.getRequestReservation(id);
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/reservation-requests/reply")
    @Operation(summary = "Reply to a reservation request")
    public ResponseEntity<RoomDto> replyToReservationRequest(@RequestBody RoomDto roomDto) {
        RoomDto updatedRoom = roomService.updaetOnRequestReservation(roomDto);
        return ResponseEntity.ok(updatedRoom);
    }

    @GetMapping("/reservations/approved")
    @Operation(summary = "Get all approved reservations")
    public ResponseEntity<List<ReservationDto>> getAllApprovedReservations() {
        List<ReservationDto> approvals = reservationService.getAllApprovmentReservation();
        return ResponseEntity.ok(approvals);
    }

    @PostMapping("/checkin-checkout")
    @Operation(summary = "Insert check-in and check-out details")
    public ResponseEntity<ReservationDto> insertCheckInAndCheckOut(@RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.insertCheckInAndCheckOut(reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }

    @PostMapping("/login")
    @Operation(summary = "Login as admin")
    public ResponseEntity<String> loginAdmin(@RequestParam String email, @RequestParam String password) {
        String result = admineService.logIn(email, password);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/available-rooms")
    @Operation(summary = "Search for available rooms by admin ID")
    public ResponseEntity<List<RoomDto>> getAllAvailableRooms(@PathVariable int id) {
        List<RoomDto> availableRooms = roomService.getAvailbleRoom(id);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/invoices/{id}")
    @Operation(summary = "Get invoice by reservation ID")
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable int id) {
        InvoiceDto invoice = invoiceService.getInvoiseForAdmine(id);
        return ResponseEntity.ok(invoice);
    }
}
