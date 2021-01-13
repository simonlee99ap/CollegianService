package com.collegian.CollegianService.repositories;

import com.collegian.CollegianService.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
