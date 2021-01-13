package com.collegian.CollegianService;

import com.collegian.CollegianService.entities.Task;
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
    CommandLineRunner initDataBase(TaskRepository repository) {
        return args -> {
            repository.save(new Task("example task 1", false, 1L, "January 15th, 2021"));
            repository.save(new Task("example task 2", true, 1L, "January 2nd, 2021"));
        };
    }
}
