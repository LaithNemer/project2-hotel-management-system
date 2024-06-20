package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Configuration.JwtService;
import com.example.HotelManagementSystem.Service.Interface.AdminInterface;
import com.example.HotelManagementSystem.auth.AuthenticationResponse;
import com.example.HotelManagementSystem.auth.AuthenticationService;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.dto.EmployeeDto;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.entity.Employee;
import com.example.HotelManagementSystem.entity.User;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
import com.example.HotelManagementSystem.repository.EmployeeRepositry;
import com.example.HotelManagementSystem.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdmineService implements AdminInterface {
    private AdmineRepositry admineRepositry;
    private UserRepositry userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authserv;
    private EmployeeRepositry employeeRepositry;





    @Autowired
    public AdmineService(AdmineRepositry admineRepositry, UserRepositry userRepository, JwtService jwtService, AuthenticationManager authenticationManager, AuthenticationService authserv,EmployeeRepositry employeeRepositry) {
        this.admineRepositry = admineRepositry;
        this.userRepository=userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authserv = authserv;
        this.employeeRepositry=employeeRepositry;
    }

    @Override
    public AdminDTO addAdmine(AdminDTO adminDTO) {

        if (adminDTO.getUser().getUsername() == null || adminDTO.getUser().getUsername().isEmpty()) {
            throw new BadRequestException("Adimn", "username");
        }
        if (adminDTO.getUser().getEmail() == null || adminDTO.getUser().getEmail().isEmpty()) {
            throw new BadRequestException("Admin", "email");
        }
        Optional<Admine>  EmailIsExit=admineRepositry.findByUserEmail(adminDTO.getUser().getEmail());
        if(EmailIsExit.isPresent()){
            System.out.println("Email Already Exist");
            throw new BadRequestException("Customer", "email"+" email is already exist");
        }

        User user=new User();
        user.setAge(adminDTO.getUser().getAge());
        user.setEmail(adminDTO.getUser().getEmail());
        user.setPassword(adminDTO.getUser().getPassword());
        user.setRole(adminDTO.getUser().getRole());
        user.setUsername(adminDTO.getUser().getUsername());
        user.setPhone(adminDTO.getUser().getPhone());


        Admine admine=new Admine();
        admine.setRole(adminDTO.getUser().getRole().name());
        admine.setSalary(adminDTO.getSalary());
        admine.setUser(user);
        user.setAdmin(admine);

        User uss= userRepository.save(user);
        adminDTO.setId(uss.getId());


        var jwtToken = jwtService.generateToken(uss);
        var refreshToken = jwtService.generateRefreshToken(uss);
        authserv.saveUserToken(uss, jwtToken);
        AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();




        return  adminDTO;



    }

    @Override
    public List<EmployeeDto> getAdllEmployee() {

        List<Employee>array=employeeRepositry.findAll();
        List<EmployeeDto>employeeDtos=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            EmployeeDto employeeDto=convertFromEmployeeToEmployeeDte(array.get(i));
            employeeDto.setId(array.get(i).getId());
            employeeDtos.add(employeeDto);
        }

        return employeeDtos;
    }

    @Override
    public User getAdmin(int id) {
        User user=userRepository.findById(id).get();

        return user;


    }
//
//    @Override
//    public AdminDTO updateAdmine(int id, AdminDTO admin) {
//
//        Admine admine=admineRepositry.getById(id);
//        admine.setSalary(admin.getSalary());
//        admine.setPhone(admin.getPhone());
//        admine.setAge(admin.getAge());
//        admine.setLastname(admin.getLastname());
//        admine.setFirstname(admin.getFirstname());
//        admine.setPassword(admin.getPassword());
//        admine.setUsername(admin.getUsername());
//        admine.setEmail(admin.getEmail());
//
//
//        admineRepositry.save(admine);
//
//
//        AdminDTO ad=getAdmin(id);
//
//
//        return  ad;
//
//
//
//
//
//    }

    @Override
    public String logIn(String email, String password) {

       Optional<User>user=userRepository.findByEmail(email);
       if(user.isPresent()){
           if(user.get().getPassword().equals(password)){
               return  "Welcome " + user.get().getUsername();
           }
       }


       return  "does not have an account using this email and password";

    }

    @Override
    public EmployeeDto insertEmployee(int idAdmin, AdminDTO adminDTO) {

       User user1=getAdmin(idAdmin);
       System.out.println(user1.getRole().name());
       String status=user1.getRole().name();

       if(!status.equals("ADMIN")){
           throw new BadRequestException("Admin", "role");
       }
        if (adminDTO.getUser().getUsername() == null || adminDTO.getUser().getUsername().isEmpty()) {
            throw new BadRequestException("Employee", "username");
        }
        if (adminDTO.getUser().getEmail() == null || adminDTO.getUser().getEmail().isEmpty()) {
            throw new BadRequestException("Employee", "email");
        }
        Optional<Admine> EmailIsExit=admineRepositry.findByUserEmail(adminDTO.getUser().getEmail());
        if(!EmailIsExit.isEmpty()){
            System.out.println("Email Already Exist");
            throw new BadRequestException("Employee", "email"+" email is already exist");
        }



        Employee employee=new Employee();
        employee.setRole(adminDTO.getRole());
        System.out.println(adminDTO.getRole());
        employee.setName(adminDTO.getUser().getUsername());
        System.out.println(adminDTO.getUser().getUsername());
        employee.setPassword(adminDTO.getUser().getPassword());
        employee.setSalary(adminDTO.getSalary());
        employee.setEmail(adminDTO.getUser().getEmail());
        employee.setAge(adminDTO.getUser().getAge());
        employee.setPhone(adminDTO.getUser().getPhone());


        employeeRepositry.save(employee);


        return  convertFromEmployeeToEmployeeDte(employee);


    }
    public EmployeeDto convertFromEmployeeToEmployeeDte(Employee employee){
        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setRole(employee.getRole());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setName(employee.getName());
        employeeDto.setAge(employee.getAge());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setPassword(employee.getPassword());

//        employeeDto.setId(employee.getId());



        return  employeeDto;

    }

    @Override
    public EmployeeDto getOneEmployee(String username) {

       Employee array=employeeRepositry.findEmployeeByName(username);
       EmployeeDto employeeDto=convertFromEmployeeToEmployeeDte(array);
       employeeDto.setId(array.getId());
       return  employeeDto;

    }

    @Override
    public EmployeeDto deleteEmployee(int id) {
        Employee employee=employeeRepositry.getById(id);
       EmployeeDto employeeDto=convertFromEmployeeToEmployeeDte(employee);
       employeeDto.setId(employee.getId());
       employeeRepositry.deleteById(id);


        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(int id, EmployeeDto employeeDto) {
        Employee employee=employeeRepositry.getById(id);
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        employee.setPassword(employeeDto.getPassword());
        employee.setEmail(employeeDto.getEmail());
        employee.setAge(employeeDto.getAge());
        employee.setPhone(employeeDto.getPhone());
        employeeRepositry.save(employee);
        employeeDto.setId(id);

        return employeeDto;
    }


    @Override
    public List<User> getAllAdmine() {

        List<User> users = userRepository.findAll();


        List<User>array=new ArrayList<>();
        for(int i=0;i<users.size();i++){

            if(users.get(i).getRole().name().equals("Admin")){
                array.add(users.get(i));

            }
        }

        return users;
    }


    private AdminDTO mapToDTO(Admine admine) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admine.getId());
        User user=userRepository.getById(admine.getUser().getId());
        adminDTO.setUser(user);
        adminDTO.setRole(admine.getRole());
        adminDTO.setSalary(admine.getSalary());

        return adminDTO;
    }
}
