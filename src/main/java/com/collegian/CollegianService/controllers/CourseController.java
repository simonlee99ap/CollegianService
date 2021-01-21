package com.collegian.CollegianService.controllers;

import com.collegian.CollegianService.assemblers.CourseModelAssembler;
import com.collegian.CollegianService.entities.Course;
import com.collegian.CollegianService.exceptions.CourseNotFoundException;
import com.collegian.CollegianService.repositories.CourseRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CourseController {
    private final CourseRepository repository;
    private final CourseModelAssembler assembler;

    CourseController(CourseRepository repository, CourseModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @ModelAttribute
    public void setHeader(HttpServletResponse response) {
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
    }

    @GetMapping("/courses")
    public CollectionModel<EntityModel<Course>> all() {
        List<EntityModel<Course>> courses = repository.findAll().stream()
                .map(course -> assembler.toModel(course))
                .collect(Collectors.toList());

        return CollectionModel.of(courses,
                linkTo(methodOn(CourseController.class).all()).withSelfRel());
    }

    @GetMapping("/courses/{id}")
    public EntityModel<Course> one(@PathVariable Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return assembler.toModel(course);
    }
}
