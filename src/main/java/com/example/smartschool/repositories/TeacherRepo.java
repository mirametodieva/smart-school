package com.example.smartschool.repositories;

import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByTeacherNum(Integer num);
    List<Teacher> findTeachersBySubjectName(String subjectName);

    @Modifying
    @Query("UPDATE Teacher SET subjectTeacher = :subject WHERE teacherNum = :teacherNum")
    void updateSubject(Subject subject, Integer teacherNum);
}
