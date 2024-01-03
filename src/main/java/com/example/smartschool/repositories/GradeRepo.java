package com.example.smartschool.repositories;

import com.example.smartschool.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long> {
    Optional<Grade> findGradeByGradeName(String name);
    List<Grade> findGradesBySubjectName(String subjectName);
}