package com.example.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "teacher")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "teacher_number")
    private Integer teacherNum;

    @Column(name = "education")
    private String education;

    // many-to-one relationship with the subject the teacher is teaching
    // (reason: there might be more than one teacher teaching the same subject)
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subjectTeacher;

    // many-to-many relationship between the teacher and the grades they are teaching
    // (reason: many teachers teach many classes)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_grade",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private Set<Grade> gradesTeacher;

    // one-to-many relationship between the teacher and the marks they had written
    // (reason: one teacher can assign many marks but one mark is assigned by only one teacher)
    @OneToMany(mappedBy = "teacherMark")
    private Set<Mark> marksTeacher;
}
