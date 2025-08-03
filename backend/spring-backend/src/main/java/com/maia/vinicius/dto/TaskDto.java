package com.maia.vinicius.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String status;
}
