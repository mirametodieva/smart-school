package com.example.smartschool.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

enum MarkValue {
    A, B, C, D, F
}

@Entity
@Table(name = "grade")
@Data
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name="value")
    @Enumerated(EnumType.STRING)
    private String value;

    @Column(name = "subject_name")
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentMark;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacherMark;

}
