package com.collegian.CollegianService.repositories;

import com.collegian.CollegianService.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
