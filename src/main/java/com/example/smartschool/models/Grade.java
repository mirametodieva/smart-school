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

    @Column(unique = true,nullable = false, name = "name")
    private String name;

//    M:M => grade : subject
    @ManyToMany(mappedBy = "gradesSubject")
    private Set<Subject> subjectsGrade;

//    M:M => grade : teacher
    @ManyToMany(mappedBy = "gradesTeacher")
    private Set<Teacher> teachersGrade;

//    1:M => grade : students
    @OneToMany(mappedBy = "gradeStudent")
    private Set<Student> studentsGrade;
}
