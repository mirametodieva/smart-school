package com.example.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "subject")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    //one-to-many relationship between the subject and the teachers teaching it
    //(reason: one subject can be taught by many teachers)
    @OneToMany(mappedBy = "subjectTeacher",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Teacher> teachersSubject;

//    many-to-many relationship between the grades and the subjects they are taught
//    (reason: one subject is studied by many grades and one grade studies many subjects)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "subject_grade",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private Set<Grade> gradesSubject; //gradesSubject but not grades because of the many-to-many relationship between teachers and grades
}
