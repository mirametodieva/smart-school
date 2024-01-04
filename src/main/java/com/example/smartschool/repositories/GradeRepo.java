package com.example.smartschool.repositories;

import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long> {
    Optional<Grade> findGradeByName(String name);
    List<Grade> findGradesBySubjectsGrade(Subject subject);
}