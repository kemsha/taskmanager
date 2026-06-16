package com.arijan.taskmanager.services;


import com.arijan.taskmanager.dto.request.TaskPatchRequest;
import com.arijan.taskmanager.dto.request.TaskPriorityRequest;
import com.arijan.taskmanager.dto.request.TaskRequest;
import com.arijan.taskmanager.dto.request.TaskStatusRequest;
import com.arijan.taskmanager.dto.response.TaskResponse;

import java.util.List;


public interface TaskServices {
    TaskResponse getTaskById(String id);
    List<TaskResponse> getAllTasks();

    TaskResponse createTask(TaskRequest request);
    TaskResponse updateTask(String id, TaskRequest request);
    TaskResponse patchTask(String id, TaskPatchRequest patchRequest);
    void deleteTask(String id);
    TaskResponse updateStatus(String id, TaskStatusRequest statusRequest);
    TaskResponse updatePriority(String id, TaskPriorityRequest priorityRequest);
}

