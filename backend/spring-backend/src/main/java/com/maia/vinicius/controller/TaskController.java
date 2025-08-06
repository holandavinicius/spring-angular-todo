package com.maia.vinicius.controller;

import com.maia.vinicius.dto.request.StatusUpdateDTO;
import com.maia.vinicius.dto.request.TaskRequestDto;
import com.maia.vinicius.service.TaskService;
import com.maia.vinicius.dto.response.TaskResponseDto;
import com.maia.vinicius.mapper.TaskMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(){
        List<TaskResponseDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("")
    public ResponseEntity<Void> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto){
        taskService.createTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@Min(1) @PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatusTask(@PathVariable Long id, @RequestBody StatusUpdateDTO statusDto){
        taskService.updateStatus(id, statusDto.getStatus());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@Valid @RequestBody TaskRequestDto taskRequestDto, @PathVariable Long id){
        TaskResponseDto updatedTask = taskService.updateTask(id, taskRequestDto);
        return ResponseEntity.ok(updatedTask);
    }
}