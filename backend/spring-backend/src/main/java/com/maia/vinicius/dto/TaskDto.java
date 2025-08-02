package com.maia.vinicius.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskDto {

    private String title;
    private String description;
    private String status;
}
