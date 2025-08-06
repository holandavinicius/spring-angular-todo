package com.maia.vinicius.dto.response;

import com.maia.vinicius.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class TaskResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Status status;

    public TaskResponseDto() {
    }

    public TaskResponseDto(Long id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
