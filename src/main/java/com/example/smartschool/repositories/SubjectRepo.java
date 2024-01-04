package com.example.smartschool.repositories;

import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Optional<Subject> findSubjectByName(String subjectName);
    List<Subject> findSubjectsByGradesSubject(Grade grades);
}
