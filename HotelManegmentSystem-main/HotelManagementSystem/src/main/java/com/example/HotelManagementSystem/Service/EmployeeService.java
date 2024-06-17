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

    List<Employee>employees= employeeRepository.findByAdmineId(id);
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
        EmployeeDto dto = new EmployeeDto();
        List<EmployeeDto>array=getAllEMployeeForAdmine(id);
        for(int i=0;i<array.size();i++){
            if(array.get(i).getUsername().equals(username)){
                Employee employee=employeeRepository.findByUsername(username);
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


            }
        }



        return  dto;
    }

    @Override
    public EmployeeDto deleteEmployee(int idadmin, String name) {


        EmployeeDto employeeDto=getEmployee(idadmin,name);
        if(employeeDto.getUsername()==null){

            throw new BadRequestException("user name ","does not exist");

        }


        employeeRepository.deleteById(employeeDto.getId());
        return  employeeDto;





    }

    @Override
    public EmployeeDto updateEmployee(int id, String  username, EmployeeDto employeeDto) {

        Admine admine=new Admine();
       List<Admine>admines=admineRepositry.findAll();
       int flags=-1;
       for(int i=0;i<admines.size();i++){
           if(admines.get(i).getId()==id){
               flags=i;

           }
       }
       if(flags==-1){
           throw  new BadRequestException("User ","because does not exsit");
       }

       EmployeeDto emp=getEmployee(id,username);
       emp.setPositoion(employeeDto.getPositoion());
       emp.setAge(employeeDto.getAge());
       emp.setLastname(employeeDto.getLastname());
       emp.setEmail(employeeDto.getEmail());
       emp.setPassword(employeeDto.getPassword());
       emp.setFirstname(employeeDto.getFirstname());
       emp.setSalary(employeeDto.getSalary());
       emp.setUsername(employeeDto.getUsername());
       EmployeeDto lastUpdate=addEmployee(emp);

       return  lastUpdate;








    }

    @Override
    public EmployeeDto deleteEmpolyeeById(int id, String name) {

        EmployeeDto employeeDto=getEmployee(id,name);

        System.out.println(employeeDto.toString());
        employeeRepository.deleteById(employeeDto.getId());
        return  employeeDto;

    }


}
