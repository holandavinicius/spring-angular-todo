package com.maia.vinicius.controller;

import com.maia.vinicius.service.TaskService;
import com.maia.vinicius.dto.TaskDto;
import com.maia.vinicius.exception.TaskCreationException;
import com.maia.vinicius.mapper.TaskMapper;
import com.maia.vinicius.model.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    @ExceptionHandler(TaskCreationException.class)
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto taskDto){
        Task task = TaskMapper.toEntity(taskDto);
        taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
