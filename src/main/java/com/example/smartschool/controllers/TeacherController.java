package com.example.smartschool.controllers;


import com.example.smartschool.dto.TeacherDto;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/fetch/teachers")
    public List<Teacher> fetchTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/fetch/teachers/{teacherNum}")
    public Teacher fetchTeacherByNum(@PathVariable Integer teacherNum) throws Exception {
        return teacherService.getTeacherByTeacherNum(teacherNum);
    }

    @GetMapping("/fetch/teachers/{subjectName}")
    public List<Teacher> fetchTeachersBySubjectName(@PathVariable String subjectName) {
        return teacherService.getTeachersBySubjectName(subjectName);
    }

    @PostMapping("/save/teachers")
    public ResponseEntity<?> saveTeacher(@RequestBody TeacherDto dto) {
        Teacher savedInDb = teacherService.saveTeacher(dto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }

    @PutMapping("/update/teachers/{teacherNum}/subject/{subjectName}")
    public ResponseEntity<?> updateTeacherSub(
            @PathVariable String subjectName,
            @PathVariable Integer teacherNum
    ) {
        teacherService.updateTeacherSubject(subjectName, teacherNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

//        @PutMapping("/update/teachers/{teacherNum}/grades/{gradeName}/add")
//    public ResponseEntity<?> updateTeacherAddGrade(
//            @PathVariable Integer teacherNum,
//            @PathVariable String gradeName
//    ) throws Exception {
//        teacherService.addGradeToTeacher(teacherNum, gradeName);
//        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
//    }

        @PutMapping("/update/teachers/{teacherNum}/grades/{gradeName}/delete")
    public ResponseEntity<?> updateTeacherDeleteGrade(
            @PathVariable Integer teacherNum,
            @PathVariable String gradeName
    ) throws Exception {
        teacherService.deleteGradeFromTeacher(teacherNum, gradeName);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

        @DeleteMapping("/delete/teachers/{teacherNum}")
        public ResponseEntity<?> deleteTeacherByTeacherNum(@PathVariable Integer teacherNum) throws Exception {
            teacherService.deleteTeacherByTeacherNum(teacherNum);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    @DeleteMapping("/delete/teachers/{teacherId}")
    public ResponseEntity<?> deleteTeacherByTeacherId(@PathVariable long teacherId) throws Exception {
        teacherService.deleteTeacherByTeacherId(teacherId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
