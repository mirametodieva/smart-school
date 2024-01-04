package com.example.smartschool.repositories;

import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByStudentNum(Integer num);
    List<Student> findStudentsByGradeStudent(Grade grade);

    @Modifying
    @Query("UPDATE Student SET gradeStudent = :grade WHERE id = :studentId")
    void updateGrade(Grade grade, Long studentId);
}
