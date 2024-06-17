package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.AdminInterface;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
//import com.example.HotelManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmineService implements AdminInterface {
    @Autowired
    private AdmineRepositry admineRepositry;



    public AdmineService(AdmineRepositry admineRepositry) {
        this.admineRepositry = admineRepositry;
    }

    @Override
    public AdminDTO addAdmine(AdminDTO adminDTO) {
        if (adminDTO.getUsername() == null || adminDTO.getUsername().isEmpty()) {
            throw new BadRequestException("Adimn", "username");
        }
        if (adminDTO.getEmail() == null || adminDTO.getEmail().isEmpty()) {
            throw new BadRequestException("Admin", "email");
        }
        boolean EmailIsExit=admineRepositry.existsByEmail(adminDTO.getEmail());
        if(EmailIsExit){
            System.out.println("Email Already Exist");
            throw new BadRequestException("Customer", "email"+" email is already exist");
        }

        Admine admine=new Admine();
        admine.setUsername(adminDTO.getUsername());
        admine.setEmail(adminDTO.getEmail());
        admine.setPassword(adminDTO.getPassword());
        admine.setAge(adminDTO.getAge());
        admine.setPhone(adminDTO.getPhone());
        admine.setFirstname(adminDTO.getFirstname());
        admine.setLastname(adminDTO.getLastname());
        admine.setSalary(adminDTO.getSalary());
        admine.setRole(adminDTO.getRole());
        Admine temp=     admineRepositry.save(admine);
        adminDTO.setId(temp.getId());

        return  adminDTO;



    }

    @Override
    public List<AdminDTO> getAdllEmployee() {
        List<Admine> admines = admineRepositry.findAll();

        List<Admine>array=new ArrayList<>();
        for(int i=0;i<admines.size();i++){
            if(!admines.get(i).getRole().equals("admin")){
                array.add(admines.get(i));

            }
        }
        return array.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdmin(int id) {
        Admine admine=admineRepositry.getById(id);
        if(!admine.getRole().equals("admin")){
          throw new BadRequestException("Admin", "role");
        }

        return  mapToDTO(admine);

    }

    @Override
    public AdminDTO updateAdmine(int id, AdminDTO admin) {

        Admine admine=admineRepositry.getById(id);
        admine.setSalary(admin.getSalary());
        admine.setPhone(admin.getPhone());
        admine.setAge(admin.getAge());
        admine.setLastname(admin.getLastname());
        admine.setFirstname(admin.getFirstname());
        admine.setPassword(admin.getPassword());
        admine.setUsername(admin.getUsername());
        admine.setEmail(admin.getEmail());


        admineRepositry.save(admine);


        AdminDTO ad=getAdmin(id);


        return  ad;





    }

    @Override
    public String logIn(String email, String password) {
        List<Admine>array=admineRepositry.findByEmail(email);
        if(array.size()==0){
            return  "no account using this email and password";
        }
        if(!array.get(0).getRole().equals("admin")){
            return  "Does not have authrized ...not admin";
        }
        int flags=-1;
        Admine admine=new Admine();

        for(int i=0;i<array.size();i++){
            if(array.get(i).getPassword().equals(password)){
                flags=i;
                admine=array.get(i);
            }
        }
        if(flags==-1){
            return "User Does not exsit...check from Password";
        }


        return  "Welcome "  + admine.getUsername();
    }

    @Override
    public AdminDTO insertEmployee(int idAdmin, AdminDTO adminDTO) {


       AdminDTO adminDTO1=getAdmin(idAdmin);
       if(!adminDTO1.getRole().equals("admin")){
           throw new BadRequestException("Admin", "role");
       }
        if (adminDTO.getUsername() == null || adminDTO.getUsername().isEmpty()) {
            throw new BadRequestException("Employee", "username");
        }
        if (adminDTO.getEmail() == null || adminDTO.getEmail().isEmpty()) {
            throw new BadRequestException("Employee", "email");
        }
        boolean EmailIsExit=admineRepositry.existsByEmail(adminDTO.getEmail());
        if(EmailIsExit){
            System.out.println("Email Already Exist");
            throw new BadRequestException("Employee", "email"+" email is already exist");
        }

        Admine admine=new Admine();
        admine.setUsername(adminDTO.getUsername());
        admine.setEmail(adminDTO.getEmail());
        admine.setPassword(adminDTO.getPassword());
        admine.setAge(adminDTO.getAge());
        admine.setPhone(adminDTO.getPhone());
        admine.setFirstname(adminDTO.getFirstname());
        admine.setLastname(adminDTO.getLastname());
        admine.setSalary(adminDTO.getSalary());
        admine.setRole(adminDTO.getRole());
        Admine temp=     admineRepositry.save(admine);
        adminDTO.setId(temp.getId());

        return  adminDTO;


    }

    @Override
    public AdminDTO getOneEmployee(String username) {
        Admine admine=admineRepositry.findByUsername(username);
        return mapToDTO(admine);



    }

    @Override
    public AdminDTO deleteEmployee(int id) {
        Admine admine=admineRepositry.getById(id);
        if (admine.getRole().equals("admin")) {

            throw new BadRequestException("admin","not employee");
        }
        admineRepositry.deleteById(id);

        return mapToDTO(admine);
    }

    @Override
    public AdminDTO updateEmployee(int id, AdminDTO adminDTO) {
        Admine admine =admineRepositry.getById(id);

        admine.setRole(adminDTO.getRole());
        admine.setLastname(adminDTO.getLastname());
        admine.setFirstname(adminDTO.getFirstname());
        admine.setPassword(adminDTO.getPassword());
        admine.setUsername(adminDTO.getUsername());
        admine.setEmail(adminDTO.getEmail());
        admine.setAge(adminDTO.getAge());
        admine.setSalary(adminDTO.getSalary());
        admine.setPhone(adminDTO.getPhone());
        admineRepositry.save(admine);

        return  mapToDTO(admine);

    }

    @Override
    public List<AdminDTO> getAllAdmine() {

        List<Admine> admines = admineRepositry.findAll();

        List<Admine>array=new ArrayList<>();
        for(int i=0;i<admines.size();i++){
            if(admines.get(i).getRole().equals("admin")){
                array.add(admines.get(i));

            }
        }
        return array.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private AdminDTO mapToDTO(Admine admine) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admine.getId());
        adminDTO.setUsername(admine.getUsername());
        adminDTO.setEmail(admine.getEmail());
        adminDTO.setPassword(admine.getPassword());
        adminDTO.setAge(admine.getAge());
        adminDTO.setPhone(admine.getPhone());
        adminDTO.setFirstname(admine.getFirstname());
        adminDTO.setLastname(admine.getLastname());
        adminDTO.setSalary(admine.getSalary());
        adminDTO.setRole(admine.getRole());
        return adminDTO;
    }
}
