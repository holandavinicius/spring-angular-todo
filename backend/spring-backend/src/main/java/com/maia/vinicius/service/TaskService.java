package com.maia.vinicius.service;

import com.maia.vinicius.dto.request.TaskRequestDto;
import com.maia.vinicius.dto.response.TaskResponseDto;
import com.maia.vinicius.enums.Status;
import com.maia.vinicius.exception.CreationException;
import com.maia.vinicius.exception.TaskDeleteException;
import com.maia.vinicius.exception.TaskNotFoundException;
import com.maia.vinicius.mapper.TaskMapper;
import com.maia.vinicius.model.Task;
import com.maia.vinicius.repository.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskResponseDto> getAllTasks(){
        try {
            return taskRepository.findAll().stream()
                    .map(taskMapper::toDto)
                    .collect(Collectors.toList());
        } catch (DataIntegrityViolationException ex) {
            throw new TaskNotFoundException("Erro: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new TaskNotFoundException("Erro", ex);
        }
    }

    public void createTask(TaskRequestDto taskCreateRequestDto) {
        try {
            Task task = taskMapper.toEntity(taskCreateRequestDto);
            taskRepository.save(task);
        } catch (DataIntegrityViolationException ex) {
            throw new CreationException("Dados inválidos para a task: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new CreationException("Erro ao salvar a task", ex);
        }
    }

    public void deleteTask(Long id){
        try{
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new TaskNotFoundException("Task com ID " + id + " não encontrada", ex);
        } catch (Exception ex) {
            throw new TaskDeleteException("Erro inesperado ao deletar a task", ex);
        }
    }

    public void updateStatus(Long id, Status status){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task com ID " + id + " não encontrada"));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task com ID " + id + " não encontrada"));
        task.setStatus(taskRequestDto.getStatus());
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }
}
