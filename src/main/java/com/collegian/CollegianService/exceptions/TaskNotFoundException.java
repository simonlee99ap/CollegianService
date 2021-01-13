package com.collegian.CollegianService.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task not found with an id " + id);
    }
}
