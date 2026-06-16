package com.arijan.taskmanager.dto.response;

import com.arijan.taskmanager.entities.TaskPriority;
import com.arijan.taskmanager.entities.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskResponse {
    private String id;
    private String name;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
