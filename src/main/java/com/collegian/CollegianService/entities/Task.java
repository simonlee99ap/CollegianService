package com.collegian.CollegianService.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Task {

    private @Id @GeneratedValue Long id;
    private String name;
    private Boolean finished;
    private Long courseId;
    private String date;

    public Task() {}

    public Task(String name, Boolean finished, Long courseId, String date) {
        this.name = name;
        this.finished = finished;
        this.courseId = courseId;
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getFinished() {
        return this.finished;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public String getDate() {
        return this.date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task task = (Task)o;
        return this.id.equals(task.id) && this.name.equals(task.name)
                && this.finished.equals(task.finished) && this.courseId.equals(task.courseId)
                && this.date.equals(task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name,
                this.finished, this.courseId, this.date);
    }

    @Override
    public String toString() {
        return "Task id: " + this.id + " name: " + this.name +
                " finished: " + this.finished + " courseId: " + this.courseId
                + " date: " + this.date;
    }
}
