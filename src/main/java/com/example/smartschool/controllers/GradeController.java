package com.example.smartschool.controllers;

import com.example.smartschool.dto.GradeDto;
import com.example.smartschool.models.Grade;
import com.example.smartschool.services.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/fetch/grades")
    public List<Grade> fetchGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/fetch/grades/{gradeName}")
    public Grade fetchGradeByGradeName(@PathVariable String gradeName) throws Exception {
        return gradeService.getGradeByName(gradeName);
    }

//    @GetMapping("/fetch/grades/{subjectName}")
//    public List<Grade> fetchGradesBySubjectName(@PathVariable String subjectName) throws Exception {
//        return gradeService.getGradesBySubjectName(subjectName);
//    }

    @PostMapping("/save/grades")
    public ResponseEntity<?> saveGrade(@RequestBody GradeDto dto) {
        Grade savedInDb = gradeService.saveGrade(dto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }

    @PutMapping("/update/grades/{gradesName}/students/{studentNum}/add")
    public ResponseEntity<?> updateGradeAddStudent(
            @PathVariable String gradeName,
            @PathVariable Integer studentNum
    )  throws Exception  {
        gradeService.addStudentToGrade(gradeName, studentNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/grades/{gradesName}/students/{studentNum}/delete")
    public ResponseEntity<?> updateGradeDeleteStudent(
            @PathVariable String gradeName,
            @PathVariable Integer studentNum
    ) throws Exception {
        gradeService.deleteStudentFromGrade(gradeName, studentNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/grades/{gradesName}/teachers/{teacherNum}/add")
    public ResponseEntity<?> updateGradeAddTeacher(
            @PathVariable String gradeName,
            @PathVariable Integer teacherNum
    )  throws Exception  {
        gradeService.addTeacherToGrade(gradeName, teacherNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/grades/{gradesName}/teachers/{teacherNum}/delete")
    public ResponseEntity<?> updateGradeDeleteTeacher(
            @PathVariable String gradeName,
            @PathVariable Integer teacherNum
    ) throws Exception {
        gradeService.deleteTeacherFromGrade(gradeName, teacherNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/grades/{gradesName}/subjects/{subjectName}/add")
    public ResponseEntity<?> updateGradeAddSubject(
            @PathVariable String gradeName,
            @PathVariable String subjectName
    )  throws Exception  {
        gradeService.addSubjectToGrade(gradeName, subjectName);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/grades/{gradesName}/subjects/{subjectName}/delete")
    public ResponseEntity<?> updateGradeDeleteSubject(
            @PathVariable String gradeName,
            @PathVariable String subjectName
    ) throws Exception {
        gradeService.deleteSubjectFromGrade(gradeName, subjectName);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/grades/{gradesName}")
    public ResponseEntity<?> deleteGradeByGradeName(@PathVariable String gradeName) throws Exception {
        gradeService.deleteGradeByGradeName(gradeName);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/grades/{gradesId}")
    public ResponseEntity<?> deleteGradeByGradeId(@PathVariable long gradeId) throws Exception {
        gradeService.deleteGradeByGradeId(gradeId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
