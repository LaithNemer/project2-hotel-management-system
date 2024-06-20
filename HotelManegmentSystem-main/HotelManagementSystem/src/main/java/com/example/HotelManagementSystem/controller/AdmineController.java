package com.example.HotelManagementSystem.controller;


import com.example.HotelManagementSystem.Service.AdmineService;
import com.example.HotelManagementSystem.Service.InvoiceService;
import com.example.HotelManagementSystem.Service.ReservationService;
import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.*;
import com.example.HotelManagementSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("adminn")
public class AdmineController {

    private AdmineService admineService;
    private InvoiceService invoiceService;
    private RoomService roomService;
    private ReservationService reservationService;

    @Autowired
    public AdmineController(AdmineService admineService,RoomService roomService,ReservationService reservationService,InvoiceService invoiceService) {
        this.admineService = admineService;
        this.reservationService=reservationService;
        this.roomService=roomService;
        this.invoiceService=invoiceService;
    }

    @PostMapping("/register")
    public ResponseEntity<AdminDTO>insertAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO adminDTO1=admineService.addAdmine(adminDTO);
        return ResponseEntity.ok(adminDTO1);
    }

    @GetMapping
    public ResponseEntity<List<User>>getAdmin() {
        List<User>array=admineService.getAllAdmine();
        return ResponseEntity.ok(array);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User>getAdminById(@PathVariable int id) {
        User adminDTO=admineService.getAdmin(id);
        return  ResponseEntity.ok(adminDTO);

    }



    @PostMapping("/insertemployee/{id}")
    public  ResponseEntity<EmployeeDto>addEmployee(@RequestBody AdminDTO adminDTO, @PathVariable int id) {
        System.out.println(id);
        EmployeeDto adminDTO1=admineService.insertEmployee(id,adminDTO);
        return ResponseEntity.ok(adminDTO1);
    }
//
    @GetMapping("/getEmployee/{username}")
    public ResponseEntity<EmployeeDto>getOneEmployee(@PathVariable(name = "username") String username){

        EmployeeDto adminDTO=admineService.getOneEmployee(username);
        return ResponseEntity.ok(adminDTO);
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeDto>>getAllEmployee(){
        List<EmployeeDto>array=admineService.getAdllEmployee();
        return  ResponseEntity.ok(array);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeDto>deleteEmployee(@PathVariable(name = "id")int id ){
        EmployeeDto adminDTO=admineService.deleteEmployee(id);
        return  ResponseEntity.ok(adminDTO);
    }



    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable(name = "id") int id,@RequestBody EmployeeDto employeeDto ){
        System.out.print(employeeDto.toString());
        EmployeeDto adminDTO1=admineService.updateEmployee(id,employeeDto);
        return  ResponseEntity.ok(adminDTO1);
    }
//
//
//
//
    @GetMapping("/ViewReservationRequest/{id}")
    public ResponseEntity<List<RoomDto>>viewReservationRequest(@PathVariable(name = "id")int id){
        List<RoomDto>array=roomService.getRequestReservation(id);
        return ResponseEntity.ok(array);


    }

    @PutMapping("/Replay")
    public ResponseEntity<RoomDto>replay(@RequestBody RoomDto roomDto){
        RoomDto roomDto1=roomService.updaetOnRequestReservation(roomDto);
        return ResponseEntity.ok(roomDto1);

    }


    @GetMapping("/getAllApprovment")
    public ResponseEntity<List<ReservationDto>>getAllAprovmentReservation(){
        List<ReservationDto>reservationDtos=reservationService.getAllApprovmentReservation();
        return  ResponseEntity.ok(reservationDtos);
    }



    @PostMapping("/checkinAndCheckOut")
    public ResponseEntity<ReservationDto>insertCheckInAndCheckOut(@RequestBody ReservationDto reservationDto){

        ReservationDto reservationDto1=reservationService.insertCheckInAndCheckOut(reservationDto);
        return  ResponseEntity.ok(reservationDto1);
    }





    @PostMapping("{email}/{password}")
    public ResponseEntity<String>LoginAdmin(@PathVariable(name = "email") String email,@PathVariable(name = "password")String password){

        String checking=admineService.logIn(email,password);

        return  ResponseEntity.ok(checking);
    }



    @GetMapping("/searchbyadmin/{idadmin}")
    public ResponseEntity<List<RoomDto>>getAllAavailableRoom(@PathVariable(name = "idadmin" )int id){
        System.out.println("laith");

        List<RoomDto>array=roomService.getAvailbleRoom(id);
        return ResponseEntity.ok(array);


    }


    @GetMapping("/getInvoice/{id}")
    public  ResponseEntity<InvoiceDto>getInvoice(@PathVariable(name = "id") int id){
        InvoiceDto invoiceDto=invoiceService.getInvoiseForAdmine(id);
        return  ResponseEntity.ok(invoiceDto);
    }




}
