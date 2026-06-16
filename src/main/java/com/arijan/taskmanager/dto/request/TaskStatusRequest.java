package com.arijan.taskmanager.dto.request;

import com.arijan.taskmanager.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusRequest {
    @NotBlank
    private TaskStatus status;
}
