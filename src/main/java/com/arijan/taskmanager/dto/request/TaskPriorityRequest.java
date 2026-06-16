package com.arijan.taskmanager.dto.request;

import com.arijan.taskmanager.entities.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskPriorityRequest {
    @NotBlank
    private TaskPriority priority;
}
