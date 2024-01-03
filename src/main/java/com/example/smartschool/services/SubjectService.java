package com.example.smartschool.services;

import com.example.smartschool.dto.SubjectDto;
import com.example.smartschool.mappers.SubjectMapper;
import com.example.smartschool.models.Subject;
import com.example.smartschool.models.Teacher;
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
public class SubjectService {
    private final SubjectRepo subjectRepo;
        private final TeacherRepo teacherRepo;
//    private final GradeRepo gradeRepo;
    private final SubjectMapper subjectMapper;

    public List<Subject> getAllSubjects() {
        //System.out.println("\u001B[32m" + " " + Thread.currentThread().getName());
        return subjectRepo.findAll();
    }

    public List<Subject> getSubjectsByGradeName(String gradeName)
    {
        String gradeNameLowered = gradeName.toLowerCase();
        return subjectRepo.findSubjectsByGradeName(gradeNameLowered);
    }

    @Transactional
    public Subject saveSubject(SubjectDto dto)
    {
        Optional<Subject> optionalSubject = subjectRepo.findSubjectBySubjectName(dto.name());
        Long id;
        if(optionalSubject.isPresent()) {
            id = optionalSubject.get().getId();
            log.info("You are updating a subject with id {}", id);
        } else {
            id = null;
            log.info("You are creating a new subject");
        }
        Subject subject = subjectMapper.convertDtoToEntity(dto, id);
        return subjectRepo.saveAndFlush(subject);
    }

    @Transactional
    public void deleteSubjectBySubjectId(long subjectId)
    {
        subjectRepo.deleteById(subjectId);
    }

    @Transactional
    public void deleteSubjectBySubjectName(String subjectName)
    {
        subjectRepo.findSubjectBySubjectName(subjectName)
                .ifPresent(subject -> subjectRepo.deleteById(subject.getId()));
    }

    @Transactional
    public void addTeacherToSubject(String subjectName, Integer teacherNum) throws Exception {
        Optional<Subject> subjectOptional = subjectRepo.findSubjectBySubjectName(subjectName);
        Optional<Teacher> teacherOptional = teacherRepo.findTeacherByTeacherNum(teacherNum);//The method findTeacherByTeacherNum should be written in TeacherRepo

        if (teacherOptional.isPresent()&&subjectOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            Subject subject=subjectOptional.get();

            subject.getTeachersSubject().add(teacher);

            subjectRepo.saveAndFlush(subject);
        } else {
            throw new Exception("Subject/Teacher not found! Check the given info!");
        }
    }

    @Transactional
    public void deleteTeacherFromSubject(String subjectName, Integer teacherNum) throws Exception {
        Optional<Subject> subjectOptional = subjectRepo.findSubjectBySubjectName(subjectName);

        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();

            subject.getTeachersSubject().removeIf(teacher -> teacher.getTeacherNum().equals(teacherNum));

            subjectRepo.saveAndFlush(subject);
        } else {
            throw new Exception("Subject not found! Check the subject's name");
        }
    }

//    @Transactional
//    public void addGradeToSubject(String subjectName, String gradeName) throws Exception {
//        Optional<Subject> subjectOptional = subjectRepo.findSubjectBySubjectName(subjectName);
//        Optional<Grade> gradeOptional = gradeRepo.findGradeByGradeName(gradeName);//The method findGradeByGradeName should be written in GradeRepo
//
//        if (subjectOptional.isPresent()&&gradeOptional.isPresent()) {
//            Subject subject = subjectOptional.get();
//            Grade grade=gradeOptional.get();
//
//            subject.getGradesSubject().add(grade);
//
//            subjectRepo.saveAndFlush(subject);
//        } else {
//            throw new Exception("Subject/Grade not found! Check the given info!");
//        }
//    }
//
    @Transactional
    public void deleteGradeFromSubject(String subjectName, String gradeName) throws Exception {
        Optional<Subject> subjectOptional = subjectRepo.findSubjectBySubjectName(subjectName);

        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();

            subject.getGradesSubject().removeIf(grade -> grade.getName().equals(gradeName));

            subjectRepo.saveAndFlush(subject);
        } else {
            throw new Exception("Subject not found! Check the subject's name");
        }
    }
}