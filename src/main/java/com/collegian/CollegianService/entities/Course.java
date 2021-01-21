package com.collegian.CollegianService.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Course {

    private @Id @GeneratedValue Long id;
    private String name;
    private String color;

    public Course() {}

    public Course(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Course)) {
            return false;
        }

        Course course = (Course)o;
        return this.id.equals(course.id)
                && this.name.equals(course.name)
                && this.color.equals(course.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.color);
    }

    @Override
    public String toString() {
        return "Course id: " + this.id + " name: " + this.name + " color: " + this.color;
    }
}
