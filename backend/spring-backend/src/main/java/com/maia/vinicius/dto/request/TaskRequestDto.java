package com.maia.vinicius.dto.request;


import com.maia.vinicius.dto.TaskBaseDto;
import com.maia.vinicius.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TaskRequestDto extends TaskBaseDto {

    public TaskRequestDto(String title, String description, Status status) {
        super(title, description, status);
    }
}