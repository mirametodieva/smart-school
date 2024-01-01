package com.example.smartschool.services;

import com.example.smartschool.dto.SubjectDto;
import com.example.smartschool.dto.TeacherDto;
import com.example.smartschool.mappers.TeacherMapper;
import com.example.smartschool.models.Grade;
import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.repositories.SubjectRepo;
import com.example.smartschool.repositories.TeacherRepo;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherService {

    private final TeacherRepo teacherRepo;
//    private final GradeRepo gradeRepo;
    private final SubjectRepo subjectRepo;
    private final TeacherMapper teacherMapper;

//    public TeacherService(TeacherRepo teacherRepo,
//                          GradeRepo gradeRepo, MarkRepo markRepo,SubjectRepo subjectRepo, TeacherMapper teacherMapper)
//    {
//        this.teacherRepo = teacherRepo;
//        this.gradeRepo = gradeRepo;
//        this.markRepo=markRepo;
//        this.subjectRepo=subjectRepo;
//        this.teacherMapper=teacherMapper;
//    }

    public List<Teacher> getAllTeachers() {
        //System.out.println("\u001B[32m" + " " + Thread.currentThread().getName());
        return teacherRepo.findAll();
    }

    public Teacher getTeacherByTeacherNum(Integer teacherNum) throws Exception {
        Optional<Teacher> teacherOptional = teacherRepo.findTeacherByTeacherNum(teacherNum);
        if (teacherOptional.isPresent()) {
            return teacherOptional.get();
        } else {
            throw new Exception("Teacher not found! Check the given number!");
        }
    }

    public List<Teacher> getTeachersBySubjectName(String subjectName)
    {
        String subjectNameLowered = subjectName.toLowerCase();
        return teacherRepo.findTeachersBySubjectName(subjectNameLowered);
    }

    @Transactional
    public Teacher saveTeacher(TeacherDto dto)
    {
        Optional<Teacher> optionalTeacher = teacherRepo.findTeacherByTeacherNum(dto.teacherNum());
        Long id;
        if(optionalTeacher.isPresent()) {
            id = optionalTeacher.get().getId();
            log.info("You are updating a teacher with id {}", id);
        } else {
            id = null;
            log.info("You are creating a new teacher");
        }
        Teacher teacher = teacherMapper.convertDtoToEntity(dto, id);
        return teacherRepo.saveAndFlush(teacher);
    }

    @Transactional
    public void deleteTeacherByTeacherId(long teacherId)
    {
        teacherRepo.deleteById(teacherId);
    }

    @Transactional
    public void deleteTeacherByTeacherNum(Integer teacherNum)
    {
        teacherRepo.findTeacherByTeacherNum(teacherNum)
                .ifPresent(teacher -> teacherRepo.deleteById(teacher.getId()));
    }

    @Transactional
    public void updateTeacherSubject(String subjectName, Integer teacherNum) {
        Subject subject = subjectRepo.findSubjectBySubjectName(subjectName).get(); //A method findSubjectBySubjectName should be added in SubjectRepo
        teacherRepo.updateSubject(subject, teacherNum);
    }

//    @Transactional
//    public void addGradeToTeacher(Integer teacherNum, String gradeName) throws Exception {
//        Optional<Teacher> teacherOptional = teacherRepo.findTeacherByTeacherNum(teacherNum);
//        Optional<Grade> gradeOptional = gradeRepo.findGradeByGradeName(gradeName);//The method findGradeByGradeName should be written in GradeRepo
//
//        if (teacherOptional.isPresent()&&gradeOptional.isPresent()) {
//            Teacher teacher = teacherOptional.get();
//            Grade grade=gradeOptional.get();
//
//            teacher.getGradesTeacher().add(grade);
//
//            teacherRepo.saveAndFlush(teacher);
//        } else {
//            throw new Exception("Teacher/Grade not found! Check the given info!");
//        }
//    }

    @Transactional
    public void deleteGradeFromTeacher(Integer teacherNum, String gradeName) throws Exception {
        Optional<Teacher> teacherOptional = teacherRepo.findTeacherByTeacherNum(teacherNum);

        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();

            teacher.getGradesTeacher().removeIf(grade -> grade.getName().equals(gradeName));

            teacherRepo.saveAndFlush(teacher);
        } else {
            throw new Exception("Teacher not found! Check the teacher's number");
        }
    }
}
