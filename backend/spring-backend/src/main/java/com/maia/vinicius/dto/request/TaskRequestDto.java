package com.maia.vinicius.dto.request;


import com.maia.vinicius.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class TaskRequestDto {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Status status;

    public TaskRequestDto() {
    }

    public TaskRequestDto(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}