package com.maia.vinicius.service;

import com.maia.vinicius.exception.TaskCreationException;
import com.maia.vinicius.model.Task;
import com.maia.vinicius.repository.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
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
            throw new TaskCreationException("Dados inválidos para a task: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new TaskCreationException("Erro ao salvar a task", ex);
        }
    }
}
