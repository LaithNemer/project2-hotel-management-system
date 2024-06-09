package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.AdmineService;
import com.example.HotelManagementSystem.Service.EmployeeService;
import com.example.HotelManagementSystem.Service.Interface.AdminInterface;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdmineController {

    @Autowired
    private AdmineService admineService;

    @Autowired
    private EmployeeService employeeService;

    public AdmineController(AdmineService admineService,EmployeeService employeeService) {
        this.admineService = admineService;
        this.employeeService = employeeService;

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

}
