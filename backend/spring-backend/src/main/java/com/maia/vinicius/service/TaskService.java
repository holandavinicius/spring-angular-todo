package com.maia.vinicius.service;

import com.maia.vinicius.dto.TaskDto;
import com.maia.vinicius.exception.CreationException;
import com.maia.vinicius.exception.TaskDeleteException;
import com.maia.vinicius.exception.TaskNotFoundException;
import com.maia.vinicius.model.Task;
import com.maia.vinicius.repository.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        try {
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

    public void updateStatus(Long id, String status){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task com ID " + id + " não encontrada"));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDto taskDto){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task com ID " + id + " não encontrada"));
        task.setStatus(taskDto.getStatus());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        return taskRepository.save(task);
    }
}
