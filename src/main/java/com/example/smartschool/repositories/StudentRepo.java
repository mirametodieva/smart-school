package com.example.smartschool.repositories;

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
    List<Student> findStudentsByGradeName(String gradeName);
    List<Student> findStudentsByGradeId(Long gradeId);

    @Modifying
    @Query("UPDATE Student SET grade_id = :gradeId WHERE id = :studentId")
    void updateGrade(Long grade, Long studentId);
}
