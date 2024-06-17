package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.SchedulingInterface;
import com.example.HotelManagementSystem.dto.SchedulingDTO;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.entity.Scheduling;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
import com.example.HotelManagementSystem.repository.SchedulingRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulingService implements SchedulingInterface {

    private SchedulingRepositry schedulingRepositry;


    AdmineRepositry admineRepositry;

//    @Autowired
//    EmployeeService employeeService;



    @Autowired
    public SchedulingService(SchedulingRepositry SchedulingRepositry,AdmineRepositry admineRepositry) {
        this.schedulingRepositry = SchedulingRepositry;
        this.admineRepositry = admineRepositry;
//        this.employeeService = employeeService;
    }


    @Override
    public SchedulingDTO insertScheduling(SchedulingDTO scheduling) {
        Scheduling scheduling1=new Scheduling();

        String name=scheduling.getEmployename();
        int idAdmine=scheduling.getAdminid();
        List<Admine>array=admineRepositry.findAll();
        int flag=-1;
        for(int i=0;i<array.size();i++){
            if(scheduling.getEmployename().equals(array.get(i).getUsername() )){
                flag=i;
            }

        }

        if(flag==-1){
            throw new BadRequestException("Employee", "employee name: " + name);
        }
        scheduling1.setNote(scheduling.getNote());
        Admine admine=admineRepositry.getById(scheduling.getAdminid());
        if(!admine.getRole().equals("admin")){
            throw new BadRequestException("Admin","role");

        }
        scheduling1.setAdmine(admine);
        scheduling1.setStatus(scheduling.getStatus());
        scheduling1.setEmployeename((scheduling.getEmployename()));
        schedulingRepositry.save(scheduling1);
        scheduling.setId(scheduling1.getId());

        return  scheduling;

    }

    @Override
    public List<SchedulingDTO> getAllScheduling(int idadmin) {

        List<Scheduling>schedulings=schedulingRepositry.findAll();
        List<SchedulingDTO> schedulingDTOS=new ArrayList<>();
        for(int i=0;i<schedulings.size();i++){
            if(idadmin==schedulings.get(i).getAdmine().getId()){
                Scheduling scheduling=schedulings.get(i);
            SchedulingDTO schedulingDTO=new SchedulingDTO();
            schedulingDTO.setId(scheduling.getId());
            schedulingDTO.setStatus(scheduling.getStatus());
            schedulingDTO.setNote(scheduling.getNote());
            schedulingDTO.setEmployename(scheduling.getEmployeename());
            schedulingDTO.setAdminid(schedulings.get(i).getAdmine().getId());
            schedulingDTOS.add(schedulingDTO);

            }
        }

        return schedulingDTOS;
    }

    @Override
    public SchedulingDTO getScheduling(int id) {
        List<Scheduling> scheduling=schedulingRepositry.findAll();
        SchedulingDTO schedulingDTO=new SchedulingDTO();

        for(int i=0;i<scheduling.size();i++){
            if(id==scheduling.get(i).getId()){
                schedulingDTO.setId(id);
                schedulingDTO.setStatus(scheduling.get(i).getStatus());
                schedulingDTO.setNote(scheduling.get(i).getNote());
                schedulingDTO.setEmployename(scheduling.get(i).getEmployeename());
                schedulingDTO.setAdminid(id);
            }
        }



        return schedulingDTO;

    }

    @Override
    public SchedulingDTO deleteScheduling(int id) {
        SchedulingDTO scheduling=getScheduling(id);
        schedulingRepositry.deleteById(id);

        return  scheduling;

    }

    @Override
    public SchedulingDTO updateScheduling(int id ,SchedulingDTO schedulingDTO) {

        Scheduling scheduling=schedulingRepositry.getById(id);
        scheduling.setStatus(schedulingDTO.getStatus());
        scheduling.setNote(schedulingDTO.getNote());
        scheduling.setEmployeename(schedulingDTO.getEmployename());
        schedulingRepositry.save(scheduling);

        schedulingDTO=getScheduling(id);
        return  schedulingDTO;








    }


}
