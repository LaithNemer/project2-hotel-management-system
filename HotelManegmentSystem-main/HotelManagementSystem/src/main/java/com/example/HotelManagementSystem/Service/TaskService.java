package com.example.HotelManagementSystem.Service;

import com.example.HotelManagementSystem.Service.Interface.TaskInterface;
import com.example.HotelManagementSystem.dto.SchedulingDTO;
import com.example.HotelManagementSystem.dto.TaskDTO;
import com.example.HotelManagementSystem.entity.Admine;
import com.example.HotelManagementSystem.entity.Scheduling;
import com.example.HotelManagementSystem.entity.Task;
import com.example.HotelManagementSystem.repository.AdmineRepositry;
import com.example.HotelManagementSystem.repository.SchedulingRepositry;
import com.example.HotelManagementSystem.repository.TaskRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements TaskInterface {

    private TaskRepositry taskRepositry;
    private SchedulingRepositry schedulingRepositry;


    @Autowired
    public TaskService(TaskRepositry taskRepositry, SchedulingRepositry schedulingRepositry) {
        this.taskRepositry = taskRepositry;
        this.schedulingRepositry=schedulingRepositry;
    }

    @Override
    public TaskDTO insertTaskForEmployee(TaskDTO taskDTO) {
        System.out.println(taskDTO.toString());
        Task task=new Task();
        task.setType(taskDTO.getType());
        task.setEndDate(taskDTO.getEndDate());
        task.setStartDate(taskDTO.getStartDate());


        List<Scheduling>array=schedulingRepositry.findAll();
        for(int i=0;i<array.size();i++){
            if(array.get(i).getId()==taskDTO.getSchedulingId()){
                Scheduling scheduling=array.get(i);
                task.setScheduling(scheduling);

            }
        }

        taskRepositry.save(task);
        taskDTO.setId(task.getId());


        return  taskDTO;


    }

    @Override
    public TaskDTO getTask(int id) {

        Task task=taskRepositry.getById(id);
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setType(task.getType());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setSchedulingId(task.getScheduling().getId());
            taskDTO.setId(task.getId());
        return taskDTO;

    }

    @Override
    public TaskDTO deleteTask(int id) {

        TaskDTO taskDTO=getTask(id);
        System.out.println(
                taskDTO.toString()
        );
        taskRepositry.deleteById(id);
        return taskDTO;
    }

    @Override
    public List<TaskDTO> getAllTask(int id) {

        List<TaskDTO>taskDTOList=new ArrayList<TaskDTO>();
        List<Task>array=taskRepositry.findAll();
        for(int i=0;i<array.size();i++){
            TaskDTO taskDTO=new TaskDTO();
            if(array.get(i).getScheduling().getId()==id){
                taskDTO.setId(array.get(i).getId());
                taskDTO.setSchedulingId(array.get(i).getScheduling().getId());
                taskDTO.setType(array.get(i).getType());
                taskDTO.setStartDate(array.get(i).getStartDate());
                taskDTO.setEndDate(array.get(i).getEndDate());
                taskDTOList.add(taskDTO);

            }
        }


        return taskDTOList;
    }
}
