package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.EmployeeInterface;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.entity.Employee;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
import com.example.HotelManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService  implements EmployeeInterface {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdmineRepositry admineRepositry;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }


    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getUsername() == null || employeeDto.getUsername().isEmpty()) {
            throw new BadRequestException("Employee", "username");
        }
        if (employeeDto.getEmail() == null || employeeDto.getEmail().isEmpty()) {
            throw new BadRequestException("employee", "email");
        }

        boolean isExist=employeeRepository.existsByUsername(employeeDto.getUsername());
        if(isExist){
            throw new IllegalArgumentException("Username already exists");
        }
        Employee employee = new Employee();
        employee.setAge(employeeDto.getAge());
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(employeeDto.getPassword());
        employee.setSalary(employeeDto.getSalary());
        employee.setPhone(employeeDto.getPhone());
        employee.setPosition(employeeDto.getPositoion());
        employee.setUsername(employeeDto.getUsername());
        int number=employeeDto.getAdmineid();
        Admine admine = admineRepositry.findById(number).get();
        employee.setAdmine(admine);
        employee.setPosition(employeeDto.getPositoion());
      Employee employee1=  employeeRepository.save(employee);
      employeeDto.setId(employee1.getId());
        return employeeDto;

    }

    @Override
    public List<EmployeeDto> getAllEMployeeForAdmine(int id) {

    List<Employee>employees= employeeRepository.findByAdmineId((long) id);
        return employees.stream().map(employee -> {
            EmployeeDto dto = new EmployeeDto();
            dto.setId(employee.getId());
            dto.setUsername(employee.getUsername());
            dto.setPassword(employee.getPassword());
            dto.setFirstname(employee.getFirstname());
            dto.setLastname(employee.getLastname());
            dto.setEmail(employee.getEmail());
            dto.setPhone(employee.getPhone());
            dto.setPositoion(employee.getPosition());
            dto.setSalary(employee.getSalary());
            dto.setAge(employee.getAge());
            dto.setAdmineid(id);
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public EmployeeDto getEmployee(int id, String username) {
        Employee employee=employeeRepository.findByUsername(username);
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setUsername(employee.getUsername());
        dto.setPassword(employee.getPassword());
        dto.setFirstname(employee.getFirstname());
        dto.setLastname(employee.getLastname());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setPositoion(employee.getPosition());
        dto.setSalary(employee.getSalary());
        dto.setAge(employee.getAge());
        dto.setAdmineid(id);


        return  dto;
    }


}
