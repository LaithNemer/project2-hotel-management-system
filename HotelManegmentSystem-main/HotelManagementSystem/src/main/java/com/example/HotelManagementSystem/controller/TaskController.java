package com.example.HotelManagementSystem.controller;

import com.example.HotelManagementSystem.Service.TaskService;
import com.example.HotelManagementSystem.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Task Controller", description = "APIs related to tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Insert a new task")
    @PostMapping
    public ResponseEntity<TaskDTO> insertTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO insertedTask = taskService.insertTaskForEmployee(taskDTO);
        return ResponseEntity.ok(insertedTask);
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@Parameter(description = "Task ID") @PathVariable(name = "id") int id) {
        TaskDTO taskDTO = taskService.getTask(id);
        return ResponseEntity.ok(taskDTO);
    }

    @Operation(summary = "Delete task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@Parameter(description = "Task ID") @PathVariable(name = "id") int id) {
        TaskDTO deletedTask = taskService.deleteTask(id);
        return ResponseEntity.ok(deletedTask);
    }

    @Operation(summary = "Get all tasks for an employee")
    @GetMapping("/all/{id}")
    public ResponseEntity<List<TaskDTO>> getAllTask(@Parameter(description = "Employee ID") @PathVariable(name = "id") int id) {
        List<TaskDTO> tasks = taskService.getAllTask(id);
        return ResponseEntity.ok(tasks);
    }
}
