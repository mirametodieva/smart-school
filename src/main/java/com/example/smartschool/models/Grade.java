package com.example.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "grade")
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

//    M:M => grade : subject
    @ManyToMany(mappedBy = "gradesS")
    private Set<Subject> subjects;

//    M:M => grade : teacher
    @ManyToMany(mappedBy = "grades")
    private Set<Teacher> teachers;

//    1:M => grade : students
    @OneToMany(mappedBy = "grade")
    private Set<Student> students;
}
