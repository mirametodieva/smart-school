package com.example.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "student_num")
    private Integer studentNum;

    @Column(name = "parent_phone")
    private String parentPhone;


//    1:M => student : mark
    @OneToMany(mappedBy = "studentMark")
    private Set<Mark> marksStudent;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade gradeStudent;
}
