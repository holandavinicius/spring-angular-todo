package com.maia.vinicius.service;

import com.maia.vinicius.exception.TaskCreationException;
import com.maia.vinicius.exception.TaskDeleteException;
import com.maia.vinicius.exception.TaskNotFoundException;
import com.maia.vinicius.model.Task;
import com.maia.vinicius.repository.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            throw new TaskCreationException("Dados inválidos para a task: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new TaskCreationException("Erro ao salvar a task", ex);
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
}
