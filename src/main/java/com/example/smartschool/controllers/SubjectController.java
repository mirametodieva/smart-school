package com.example.smartschool.controllers;

import com.example.smartschool.dto.SubjectDto;
import com.example.smartschool.models.Subject;
import com.example.smartschool.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/fetch/subjects")
    public List<Subject> fetchSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/fetch/subjects/{gradeName}")
    public List<Subject> fetchSubjectsByGradeName(@PathVariable String gradeName) {
        return subjectService.getSubjectsByGradeName(gradeName);
    }

    @PostMapping("/save/subjects")
    public ResponseEntity<?> saveSubject(@RequestBody SubjectDto dto) {
        Subject savedInDb = subjectService.saveSubject(dto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }

    @PutMapping("/update/subjects/{subjectName}/teachers/{teacherNum}/add")
    public ResponseEntity<?> updateSubjectAddTeacher(
            @PathVariable String subjectName,
            @PathVariable Integer teacherNum
    )  throws Exception  {
        subjectService.addTeacherToSubject(subjectName, teacherNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/subjects/{subjectName}/teachers/{teacherNum}/delete")
    public ResponseEntity<?> updateSubjectDeleteTeacher(
            @PathVariable String subjectName,
            @PathVariable Integer teacherNum
    ) throws Exception {
        subjectService.deleteTeacherFromSubject(subjectName, teacherNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/subjects/{subjectName}/grades/{gradeName}/add")
    public ResponseEntity<?> updateSubjectAddGrade(
            @PathVariable String subjectName,
            @PathVariable String gradeName
    )  throws Exception  {
        subjectService.addGradeToSubject(subjectName, gradeName);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/subjects/{subjectName}/grades/{gradeName}/delete")
    public ResponseEntity<?> updateSubjectDeleteGrade(
            @PathVariable String subjectName,
            @PathVariable String gradeName
    ) throws Exception {
        subjectService.deleteGradeFromSubject(subjectName, gradeName);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/subjects/{subjectName}")
    public ResponseEntity<?> deleteSubjectBySubjectName(@PathVariable String subjectName) {
        subjectService.deleteSubjectBySubjectName(subjectName);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
