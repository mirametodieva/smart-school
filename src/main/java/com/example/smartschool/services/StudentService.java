package com.example.smartschool.services;

import com.example.smartschool.dto.MarkDto;
import com.example.smartschool.dto.StudentDto;
import com.example.smartschool.mappers.MarkMapper;
import com.example.smartschool.mappers.StudentMapper;
import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Mark;
import com.example.smartschool.models.Student;
import com.example.smartschool.repositories.GradeRepo;
import com.example.smartschool.repositories.MarkRepo;
import com.example.smartschool.repositories.StudentRepo;
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
public class StudentService {
    private final StudentRepo studentRepo;
    private final GradeRepo gradeRepo;
    private final StudentMapper studentMapper;
    private final MarkMapper markMapper;
    private final MarkRepo markRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentByStudentNum(Integer studentNum) throws Exception {
        return studentRepo.findStudentByStudentNum(studentNum)
                .orElseThrow(() -> new Exception("Student not found with student number: " + studentNum));
    }

    public List<Student> getStudentsByGradeName(String gradeName)
    {
        return studentRepo.findStudentsByGradeName(gradeName.toLowerCase());
    }

    @Transactional
    public Student saveStudent(StudentDto dto)
    {
        Integer studentNum = dto.studentNum();
        Optional<Student> existingStudent = studentRepo.findStudentByStudentNum(studentNum);

        if (existingStudent.isPresent()) {
            Long id = existingStudent.get().getId();
            log.info("You are updating a student with id {}", id);
            return studentRepo.saveAndFlush(studentMapper.convertDtoToEntity(dto, id));
        } else {
            log.info("You are creating a new student");
            return studentRepo.saveAndFlush(studentMapper.convertDtoToEntity(dto, null));
        }
    }

    @Transactional
    public void deleteStudentByStudentNum(Integer studentNum)
    {
        studentRepo.findStudentByStudentNum(studentNum)
                .ifPresent(student -> studentRepo.deleteById(student.getId()));
    }

    @Transactional
    public void updateStudentGrade(String gradeName, Integer studentNum) {
        Optional<Grade> grade = gradeRepo.findGradeByGradeName(gradeName);
        if(grade.isPresent()){
            studentRepo.findStudentByStudentNum(studentNum)
                    .ifPresent(student -> studentRepo.updateGrade(grade.get(), student.getId()));
            log.info("Updated grade for student with student number {}", studentNum);
        }
    }

    @Transactional
    public void saveMarkForStudent(Long studentId, MarkDto markDto) throws Exception {
        Optional<Student> studentOptional = studentRepo.findById(studentId);

        if(studentOptional.isPresent()) {
            Student student=studentOptional.get();
            Mark mark = markMapper.convertDtoToEntity(markDto, null);
            mark.setStudentMark(student);
            markRepo.saveAndFlush(mark);
            student.getMarksStudent().add(mark);
            studentRepo.saveAndFlush(student);
        }
        else
            throw new Exception("Student not found! Check the given info!");
    }
}
