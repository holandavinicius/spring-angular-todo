package com.maia.vinicius.controller;

import com.maia.vinicius.exception.TaskDeleteException;
import com.maia.vinicius.service.TaskService;
import com.maia.vinicius.dto.TaskDto;
import com.maia.vinicius.exception.TaskCreationException;
import com.maia.vinicius.mapper.TaskMapper;
import com.maia.vinicius.model.Task;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createTask(@Valid @RequestBody TaskDto taskDto){
        Task task = TaskMapper.toEntity(taskDto);
        taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@Min(1) @PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatusTask(@PathVariable Long id, @RequestBody String status){
        taskService.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable Long id){
        Task updatedTask = taskService.updateTask(id, taskDto);
        TaskDto responseDto = TaskMapper.toDto(updatedTask);
        return ResponseEntity.ok(responseDto);
    }
}
