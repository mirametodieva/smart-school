package com.example.smartschool.repositories;

import com.example.smartschool.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepo extends JpaRepository<Mark, Long> {
    List<Mark> findMarksByTeacherNum(Integer teacherNum);
    List<Mark> findMarksByStudentNum(Integer studentNum);

}
