package com.arijan.taskmanager.repositories;

import com.arijan.taskmanager.entities.Task;
import org.springframework.data.repository.ListCrudRepository;


public interface TaskRepository extends ListCrudRepository<Task, String> {
}
