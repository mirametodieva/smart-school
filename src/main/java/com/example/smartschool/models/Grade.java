package com.example.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


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
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "grade_subject",
//            joinColumns = @JoinColumn(name = "grade_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id"))
//    private Set<Subject> subjects;

//    M:M => grade : teacher
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "grade_teacher",
//            joinColumns = @JoinColumn(name = "grade_id"),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
//    private Set<Teacher> teachers;

//    1:M => grade : students
//    @OneToMany(mappedBy = "grade")
//    @JsonIgnore
//    private Set<Student> students;
}
