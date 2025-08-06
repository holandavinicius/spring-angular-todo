package com.maia.vinicius.dto.response;

import com.maia.vinicius.dto.TaskBaseDto;
import com.maia.vinicius.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TaskResponseDto extends TaskBaseDto {

    @NotNull
    private Long id;

    public TaskResponseDto(String title, String description, Status status, Long id) {
        super(title, description, status);
        this.id = id;
    }
}
