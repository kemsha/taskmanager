package com.arijan.taskmanager.mapper;

import com.arijan.taskmanager.dto.request.TaskPatchRequest;
import com.arijan.taskmanager.dto.request.TaskRequest;
import com.arijan.taskmanager.dto.response.TaskResponse;
import com.arijan.taskmanager.entities.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequest taskRequest){
        Task entity = new Task();
        entity.setName(taskRequest.getName());
        entity.setDescription(taskRequest.getDescription());
        entity.setPriority(taskRequest.getPriority());
        entity.setDueDate(taskRequest.getDueDate());
        return entity;
    }
    public TaskResponse toResponse(Task task){
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setName(task.getName());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setDescription(task.getDescription());
        response.setDueDate(task.getDueDate());
        response.setCreatedDate(task.getCreatedDate());
        response.setUpdatedDate(task.getUpdatedDate());
        return response;
    }
    public List<TaskResponse> toResponseList(List<Task> taskList) {
        return taskList.stream().map(this::toResponse).toList();
    }
    public void applyPatch(Task task, TaskPatchRequest patchRequest) {
        if (patchRequest.getName() != null) {
            task.setName(patchRequest.getName());
        }
        if (patchRequest.getDescription() != null) {
            task.setDescription(patchRequest.getDescription());
        }
        if(patchRequest.getPriority() != null) {
            task.setPriority(patchRequest.getPriority());
        }
        if(patchRequest.getStatus() != null) {
            task.setStatus(patchRequest.getStatus());
        }
        if(patchRequest.getDueDate() != null) {
            task.setDueDate(patchRequest.getDueDate());
        }
    }
}
