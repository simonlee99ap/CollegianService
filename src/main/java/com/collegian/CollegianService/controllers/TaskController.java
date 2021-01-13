package com.collegian.CollegianService.controllers;

import com.collegian.CollegianService.entities.Task;
import com.collegian.CollegianService.exceptions.TaskNotFoundException;
import com.collegian.CollegianService.repositories.TaskRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    List<Task> all() {
        return repository.findAll();
    }

    @GetMapping("/tasks/{id}")
    Task one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping("/tasks")
    Task newTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @PutMapping("/tasks/{id}")
    Task modifyTask(@RequestBody Task newTask, @PathVariable Long id) {
        return repository.findById(id)
                .map(task -> {
                    task.setName(newTask.getName());
                    task.setFinished(newTask.getFinished());
                    task.setClassId(newTask.getClassId());
                    return repository.save(task);
                }).orElseGet(() -> {
                    newTask.setId(id);
                    return repository.save(newTask);
                });
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
