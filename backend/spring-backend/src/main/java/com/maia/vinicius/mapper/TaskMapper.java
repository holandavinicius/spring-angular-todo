package com.maia.vinicius.mapper;

import com.maia.vinicius.dto.TaskDto;
import com.maia.vinicius.model.Task;

public class TaskMapper {

    public static Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}
