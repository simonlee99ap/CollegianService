package com.collegian.CollegianService;

import com.collegian.CollegianService.entities.Course;
import com.collegian.CollegianService.entities.Task;
import com.collegian.CollegianService.repositories.CourseRepository;
import com.collegian.CollegianService.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initTaskDataBase(TaskRepository repository) {
        return args -> {
            repository.save(new Task("read book", false, 3L, "Jan 15 2021"));
            repository.save(new Task("do homework", false, 3L, "Jan 02 2021"));
        };
    }

    @Bean
    CommandLineRunner initCourseDatabase(CourseRepository repository) {
        return args -> {
            repository.save(new Course("15122 - Principles of Imperative Computation", "blue"));
            repository.save(new Course("15150 - Principles of Functional Programming", "red"));
        };
    }
}
