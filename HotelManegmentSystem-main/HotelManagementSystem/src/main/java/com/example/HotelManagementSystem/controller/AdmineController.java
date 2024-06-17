package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.AdmineService;
import com.example.HotelManagementSystem.Service.EmployeeService;
import com.example.HotelManagementSystem.Service.Interface.AdminInterface;
import com.example.HotelManagementSystem.Service.RoomService;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.dto.RoomDto;
import com.example.HotelManagementSystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdmineController {

    private AdmineService admineService;

    private EmployeeService employeeService;

    private RoomService roomService;

    @Autowired
    public AdmineController(AdmineService admineService,EmployeeService employeeService,RoomService roomService) {
        this.admineService = admineService;
        this.employeeService = employeeService;
        this.roomService=roomService;
    }

    @PostMapping
    public ResponseEntity<AdminDTO>insertAdmin(@RequestBody AdminDTO adminDTO) {

        AdminDTO adminDTO1=admineService.addAdmine(adminDTO);
        return ResponseEntity.ok(adminDTO1);
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>>getAdmin() {
        List<AdminDTO>array=admineService.getAdllEmployee();
        return ResponseEntity.ok(array);

    }

    @GetMapping("{id}")
    public ResponseEntity<AdminDTO>getAdminById(@PathVariable int id) {
        AdminDTO adminDTO=admineService.getAdmin(id);
        return  ResponseEntity.ok(adminDTO);

    }

    @PostMapping("addemployee")
    public ResponseEntity<EmployeeDto>updateAdmin( @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1=employeeService.addEmployee(employeeDto);
        return ResponseEntity.ok(employeeDto1);

    }

    @GetMapping("getemployees/{id}")
    public ResponseEntity<List<EmployeeDto>>getEmployee(@PathVariable int id) {
        List<EmployeeDto>array=employeeService.getAllEMployeeForAdmine(id);
        return ResponseEntity.ok(array);



    }

    @GetMapping("getemployee/{id}/{username}")
    public ResponseEntity<EmployeeDto>getEmployeeById(@PathVariable int id,@PathVariable String username) {
        EmployeeDto employeeDto=employeeService.getEmployee(id,username);
        return  ResponseEntity.ok(employeeDto);

    }

    @DeleteMapping("/deleteEmployee/{idadmin}/{name}")
    public ResponseEntity<EmployeeDto>DeleteEmployee(@PathVariable(name = "idadmin") int idadmin,@PathVariable(name = "name")String name ){

        EmployeeDto employeeDto=employeeService.deleteEmployee(idadmin,name);
        return  ResponseEntity.ok(employeeDto);

    }


    @PutMapping("{id}")
    public  ResponseEntity<AdminDTO>updateAdmine(@PathVariable int id ,@RequestBody AdminDTO admin){
        AdminDTO adminDTO=admineService.updateAdmine(id,admin);
        return ResponseEntity.ok(adminDTO);


    }

    @PutMapping("{id}/{username}")
    public ResponseEntity<EmployeeDto>updateEmployee(@PathVariable(name = "id") int id ,@PathVariable(name = "username") String username,@RequestBody EmployeeDto employeeDto){

        EmployeeDto employeeDto1=employeeService.updateEmployee(id,username,employeeDto);
        return  ResponseEntity.ok(employeeDto1);


    }

    @DeleteMapping("{id}/{name}")
    public  ResponseEntity<EmployeeDto>deleteEmployee(@PathVariable(name = "id")int id ,@PathVariable String name ){
        EmployeeDto employeeDto=employeeService.deleteEmpolyeeById(id,name);
        return  ResponseEntity.ok(employeeDto);
    }



    @PostMapping("{email}/{password}")
    public ResponseEntity<String>LoginAdmin(@PathVariable(name = "email") String email,@PathVariable(name = "password")String password){
        String checking=admineService.logIn(email,password);

        return  ResponseEntity.ok(checking);
    }



    @GetMapping("searchbyadmin/{idadmin}")
    public ResponseEntity<List<RoomDto>>getAllAavailableRoom(@PathVariable(name = "idadmin" )int id){
        List<RoomDto>array=roomService.getAvailbleRoom(id);
        return ResponseEntity.ok(array);


    }




}
