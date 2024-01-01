package com.example.smartschool.repositories;

import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Optional<Subject> findSubjectBySubjectName(String subjectName);
    List<Subject> findSubjectsByGradeName(String gradeName);
}
