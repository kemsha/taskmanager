package com.arijan.taskmanager.controllers;

import com.arijan.taskmanager.dto.request.TaskPatchRequest;
import com.arijan.taskmanager.dto.request.TaskPriorityRequest;
import com.arijan.taskmanager.dto.request.TaskRequest;
import com.arijan.taskmanager.dto.request.TaskStatusRequest;
import com.arijan.taskmanager.dto.response.TaskResponse;
import com.arijan.taskmanager.services.TaskServices;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/tasks")
public class TaskControllers {
    private final TaskServices taskServices;

    public TaskControllers(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable String id) {
        return ResponseEntity.ok(taskServices.getTaskById(id));
    }
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskServices.getAllTasks());
    }
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskServices.createTask(taskRequest));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable String id, @Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(taskServices.updateTask(id, taskRequest));
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<TaskResponse> patchTask(@PathVariable String id, @Valid @RequestBody TaskPatchRequest patchRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(taskServices.patchTask(id, patchRequest));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskServices.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(@PathVariable String id, @RequestBody TaskStatusRequest statusRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(taskServices.updateStatus(id,statusRequest));
    }
    @PatchMapping(value = "/{id}/priority")
    public ResponseEntity<TaskResponse> updatePriority(@PathVariable String id, @RequestBody TaskPriorityRequest priorityRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(taskServices.updatePriority(id,priorityRequest));
    }
}
