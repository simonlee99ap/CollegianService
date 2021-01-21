package com.collegian.CollegianService.controllers;

import com.collegian.CollegianService.assemblers.TaskModelAssembler;
import com.collegian.CollegianService.entities.Task;
import com.collegian.CollegianService.exceptions.TaskNotFoundException;
import com.collegian.CollegianService.repositories.TaskRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TaskController {
    private final TaskRepository repository;
    private final TaskModelAssembler assembler;

    TaskController(TaskRepository repository, TaskModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @ModelAttribute
    public void setHeader(HttpServletResponse response) {
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
    }

    @GetMapping("/tasks")
    public CollectionModel<EntityModel<Task>> all() {
        List<EntityModel<Task>> tasks = repository.findAll().stream()
                .map(task -> assembler.toModel(task))
                .collect(Collectors.toList());

        return CollectionModel.of(tasks,
                linkTo(methodOn(TaskController.class).all()).withSelfRel());
    }

    @GetMapping("/tasks/{id}")
    public EntityModel<Task> one(@PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        return assembler.toModel(task);
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
                    task.setCourseId(newTask.getCourseId());
                    task.setDate(newTask.getDate());
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
