package com.example.smartschool.services;

import com.example.smartschool.dto.StudentDto;
import com.example.smartschool.mappers.StudentMapper;
import com.example.smartschool.models.Student;
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
    private final StudentMapper studentMapper;


    public StudentService(StudentRepo studentRepo, StudentMapper studentMapper) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(Long id) throws Exception {
        return studentRepo.findById(id)
                .orElseThrow(() -> new Exception("Student not found with id: " + id));
    }

    public Student getStudentByStudentNum(Integer studentNum) throws Exception {
        return studentRepo.findStudentByStudentNum(studentNum)
                .orElseThrow(() -> new Exception("Student not found with student number: " + studentNum));
    }

    public List<Student> getStudentsByGradeName(String gradeName)
    {
        return studentRepo.findStudentsByGradeName(gradeName.toLowerCase());
    }

    public List<Student> getStudentsByGradeId(Long gradeId)
    {
        return studentRepo.findStudentsByGradeId(gradeId);
    }

    @Transactional
    public Student saveStudent(StudentDto dto)
    {
        Integer studentNum = dto.getStudentNum();
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
    public void deleteStudentById(long id)
    {
        studentRepo.deleteById(id);
    }

    @Transactional
    public void deleteStudentByStudentNum(Integer studentNum)
    {
        studentRepo.findStudentByStudentNum(studentNum)
                .ifPresent(student -> studentRepo.deleteById(student.getId()));
    }

    @Transactional
    public void updateStudentGrade(Long gradeId, Integer studentNum) {
//        Grade grade = gradeRepo.findGradeByGradeName(gradeName).getId();}
        studentRepo.findStudentByStudentNum(studentNum)
                .ifPresent(student -> studentRepo.updateGrade(gradeId, student.getId()));
        log.info("Updated grade for student with student number {}", studentNum);
    }

//    @Transactional
//    public Student saveMarkForStudent(Long studentId, MarkDto markDto) {
//        Optional<Student> student = studentRepo.findById(studentId);
//
//        Mark mark = markMapper.convertDtoToEntity(markDto);
//        mark.setStudentMark(student);
//        markRepo.saveAndFlush(mark);
//        student.getMarksStudent().add(mark);
//
//        return studentRepo.saveAndFlush(student);
//    }
}
