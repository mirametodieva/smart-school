package com.example.smartschool.services;

import com.example.smartschool.dto.GradeDto;
import com.example.smartschool.mappers.GradeMapper;
import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Student;
import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.repositories.GradeRepo;
import com.example.smartschool.repositories.StudentRepo;
import com.example.smartschool.repositories.SubjectRepo;
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
public class GradeService {
    private final GradeRepo gradeRepo;
    private final StudentRepo studentRepo;
    private final TeacherRepo teacherRepo;
    private final SubjectRepo subjectRepo;
    private final GradeMapper gradeMapper;

    public List<Grade> getAllGrades() {
        return gradeRepo.findAll();
    }

    public Grade getGradeByName(String gradeName) throws Exception {
        return gradeRepo.findGradeByName(gradeName)
                .orElseThrow(() -> new Exception("Grade not found with grade number: " + gradeName));
    }

    public List<Grade> getGradesBySubjectName(String subjectName) throws Exception {
        Optional<Subject> subject = subjectRepo.findSubjectByName(subjectName.toLowerCase());
        if(subject.isPresent()){
            return gradeRepo.findGradesBySubjectsGrade(subject.get());
        }
        else{
            throw new Exception("Check subject name.");
        }
    }

    @Transactional
    public Grade saveGrade(GradeDto dto) {
        String gradeName = dto.name();
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);

        if (existingGrade.isPresent()) {
            Long id = existingGrade.get().getId();
            log.info("You are updating a grade with id {}", id);
            return gradeRepo.saveAndFlush(gradeMapper.convertDtoToEntity(dto, id));
        } else {
            log.info("You are creating a new grade");
            return gradeRepo.saveAndFlush(gradeMapper.convertDtoToEntity(dto, null));
        }
    }

    @Transactional
    public void deleteGradeByGradeName(String gradeName) {
        gradeRepo.findGradeByName(gradeName)
                .ifPresent(grade -> gradeRepo.deleteById(grade.getId()));
    }

    @Transactional
    public void addStudentToGrade(String gradeName, Integer studentNum) throws Exception {
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);
        Optional<Student> existingStudent = studentRepo.findStudentByStudentNum(studentNum);

        if (existingStudent.isPresent() && existingGrade.isPresent()) {
            Student student = existingStudent.get();
            Grade grade = existingGrade.get();

            grade.getStudentsGrade().add(student);

            gradeRepo.saveAndFlush(grade);
        } else {
            throw new Exception("Unable to add the student to the grade");
        }
    }

    @Transactional
    public void deleteStudentFromGrade(String gradeName, Integer studentNum) throws Exception {
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);

        if (existingGrade.isPresent()) {
            Grade grade = existingGrade.get();

            grade.getStudentsGrade().removeIf(student -> student.getStudentNum().equals(studentNum));

            gradeRepo.saveAndFlush(grade);
        } else {
            throw new Exception("Grade not found with grade name: " + gradeName);
        }
    }

    @Transactional
    public void addTeacherToGrade(String gradeName, Integer teacherNum) throws Exception {
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);
        Optional<Teacher> existingTeacher = teacherRepo.findTeacherByTeacherNum(teacherNum);

        if (existingTeacher.isPresent() && existingGrade.isPresent()) {
            Teacher teacher = existingTeacher.get();
            Grade grade = existingGrade.get();

            grade.getTeachersGrade().add(teacher);

            gradeRepo.saveAndFlush(grade);
        } else {
            throw new Exception("Unable to add the teacher to the grade");
        }
    }

    @Transactional
    public void deleteTeacherFromGrade(String gradeName, Integer teacherNum) throws Exception {
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);

        if (existingGrade.isPresent()) {
            Grade grade = existingGrade.get();

            grade.getTeachersGrade().removeIf(teacher -> teacher.getTeacherNum().equals(teacherNum));

            gradeRepo.saveAndFlush(grade);
        } else {
            throw new Exception("Grade not found with grade name: " + gradeName);
        }
    }

    @Transactional
    public void addSubjectToGrade(String gradeName, String subjectName) throws Exception {
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);
        Optional<Subject> existingSubject = subjectRepo.findSubjectByName(subjectName);

        if (existingSubject.isPresent() && existingGrade.isPresent()) {
            Subject subject = existingSubject.get();
            Grade grade = existingGrade.get();

            grade.getSubjectsGrade().add(subject);

            gradeRepo.saveAndFlush(grade);
        } else {
            throw new Exception("Unable to add the subject to the grade");
        }
    }

    @Transactional
    public void deleteSubjectFromGrade(String gradeName, String subjectName) throws Exception {
        Optional<Grade> existingGrade = gradeRepo.findGradeByName(gradeName);

        if (existingGrade.isPresent()) {
            Grade grade = existingGrade.get();

            grade.getSubjectsGrade().removeIf(subject -> subject.getName().equals(subjectName));

            gradeRepo.saveAndFlush(grade);
        } else {
            throw new Exception("Grade not found with grade name: " + gradeName);
        }
    }
}
