package com.example.demo;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "sequence_generator"
    )
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT", nullable = false)
    private String name;

    @Column(name = "department", columnDefinition = "TEXT", nullable = false)
    private String department;

    @ManyToMany(
            cascade = {PERSIST, REMOVE},
            mappedBy = "courses"
    )
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}