package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.TaskService;
import com.example.HotelManagementSystem.dto.TaskDTO;
import com.example.HotelManagementSystem.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController (TaskService taskService) {
        this.taskService = taskService;

    }

    @PostMapping
    public ResponseEntity<TaskDTO>insertTask(@RequestBody TaskDTO taskDTO) {



        TaskDTO insert=taskService.insertTaskForEmployee(taskDTO);
        return  ResponseEntity.ok(insert);
    }


    @GetMapping ("{id}")
    public ResponseEntity<TaskDTO>getTask(@PathVariable(name = "id") int id){

        TaskDTO taskDTO=taskService.getTask(id);
        return  ResponseEntity.ok(taskDTO);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<TaskDTO>deleteTask(@PathVariable(name = "id") int id){
        System.out.println(id);
        TaskDTO taskDTO=taskService.deleteTask(id);
        return ResponseEntity.ok(taskDTO);



    }


    @GetMapping("getalltask/{id}")
    public ResponseEntity<List<TaskDTO>>getAllTask(@PathVariable(name = "id") int id){
        List<TaskDTO>array=taskService.getAllTask(id);
        return  ResponseEntity.ok(array);

    }

}
