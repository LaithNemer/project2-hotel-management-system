package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.AdminInterface;
import com.example.HotelManagementSystem.dto.AdminDTO;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
import com.example.HotelManagementSystem.repository.EmployeeRepository;
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
        Admine temp=     admineRepositry.save(admine);
        adminDTO.setId(temp.getId());

        return  adminDTO;



    }

    @Override
    public List<AdminDTO> getAdllEmployee() {
        List<Admine> admines = admineRepositry.findAll();
        return admines.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdmin(int id) {
        Admine admine=admineRepositry.getById(id);
        return  mapToDTO(admine);

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
        return adminDTO;
    }
}
