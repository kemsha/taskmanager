package com.arijan.taskmanager.services;

import com.arijan.taskmanager.dto.request.TaskPatchRequest;
import com.arijan.taskmanager.dto.request.TaskPriorityRequest;
import com.arijan.taskmanager.dto.request.TaskRequest;
import com.arijan.taskmanager.dto.request.TaskStatusRequest;
import com.arijan.taskmanager.dto.response.TaskResponse;
import com.arijan.taskmanager.entities.Task;
import com.arijan.taskmanager.exceptions.InvalidTaskStatusChangeException;
import com.arijan.taskmanager.mapper.TaskMapper;
import com.arijan.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.arijan.taskmanager.exceptions.TaskNotFoundException;

import static com.arijan.taskmanager.entities.TaskStatus.*;

@Service
public class TaskServiceImpl implements TaskServices {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }
    public TaskResponse getTaskById(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return taskMapper.toResponse(task);
    }
    public List<TaskResponse> getAllTasks(){
        return taskMapper.toResponseList(taskRepository.findAll());
    }
    public TaskResponse createTask(TaskRequest taskRequest){
        Task newTask = taskMapper.toEntity(taskRequest);
        newTask.setStatus(CREATED);
        newTask.setCreatedDate(LocalDateTime.now());
        return finaliseTask(newTask);
    }
    public TaskResponse updateTask(String id, TaskRequest taskRequest ) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());
        return finaliseTask(task);
    }
    public TaskResponse patchTask(String id, TaskPatchRequest patchRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskMapper.applyPatch(task, patchRequest);
        return finaliseTask(task);
    }
    public void deleteTask(String id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
    public TaskResponse updateStatus(String id, TaskStatusRequest statusRequest){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        if (task.getStatus() == CREATED && statusRequest.getStatus() == IN_PROGRESS) {
            task.setStatus(statusRequest.getStatus());
        } else if (task.getStatus() == IN_PROGRESS && statusRequest.getStatus() == COMPLETED) {
            task.setStatus(statusRequest.getStatus());
        } else {
            throw new InvalidTaskStatusChangeException("Invalid status change");
        }
        return finaliseTask(task);
    }

    public TaskResponse updatePriority(String id, TaskPriorityRequest priorityRequest){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        if (priorityRequest.getPriority() == null || priorityRequest.getPriority() == task.getPriority()) {
            return finaliseTask(task);
        } else {
            task.setPriority(priorityRequest.getPriority());
        }
        return finaliseTask(task);
    }

    private TaskResponse finaliseTask(Task task) {
        task.setUpdatedDate(LocalDateTime.now());
        return taskMapper.toResponse(taskRepository.save(task));
    }
}
