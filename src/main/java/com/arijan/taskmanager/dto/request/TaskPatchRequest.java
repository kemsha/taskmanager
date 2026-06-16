package com.arijan.taskmanager.dto.request;

import com.arijan.taskmanager.entities.TaskPriority;
import com.arijan.taskmanager.entities.TaskStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskPatchRequest {
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @Size(min = 3, max = 300, message = "Description must be between 3 and 300 characters")
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    @Future
    private LocalDateTime dueDate;
}
