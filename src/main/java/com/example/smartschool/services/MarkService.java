package com.example.smartschool.services;

import com.example.smartschool.mappers.MarkMapper;
import com.example.smartschool.models.Mark;
import com.example.smartschool.models.Student;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.repositories.MarkRepo;
import com.example.smartschool.repositories.StudentRepo;
import com.example.smartschool.repositories.TeacherRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MarkService {
    private final MarkRepo markRepo;
    private final StudentRepo studentRepo;
    private final TeacherRepo teacherRepo;

    public List<Mark> getMarkByTeacherNum(Integer teacherNum) throws Exception {
        Optional<Teacher> teacherOptional = teacherRepo.findTeacherByTeacherNum(teacherNum);
        if (teacherOptional.isPresent()) {
            return markRepo.findMarksByTeacherNum(teacherNum);
        } else {
            throw new Exception("Teacher not found! Check the given number!");
        }
    }

    public List<Mark> getMarkByStudentNum(Integer studentNum) throws Exception {
        Optional<Student> studentOptional = studentRepo.findStudentByStudentNum(studentNum);
        if (studentOptional.isPresent()) {
            return markRepo.findMarksByStudentNum(studentNum);
        } else {
            throw new Exception("Student not found! Check the given number!");
        }
    }

    @Transactional
    public void deleteMarkByMarkId(long markId)
    {
        markRepo.findById(markId)
                .ifPresent(mark -> markRepo.deleteById(mark.getId()));
    }
}
