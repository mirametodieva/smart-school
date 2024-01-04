package com.example.smartschool.repositories;

import com.example.smartschool.models.Mark;
import com.example.smartschool.models.Student;
import com.example.smartschool.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Long> {
    List<Mark> findMarksByTeacherMark(Teacher teacher);
    List<Mark> findMarksByStudentMark(Student student);

}
