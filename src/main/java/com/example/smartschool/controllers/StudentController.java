package com.example.smartschool.controllers;

import com.example.smartschool.dto.StudentDto;
import com.example.smartschool.models.Student;
import com.example.smartschool.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/fetch/students")
    public List<Student> fetchStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/fetch/students/{studentNum}")
    public Student fetchStudentByStudentNum(@PathVariable Integer studentNum) throws Exception {
        return studentService.getStudentByStudentNum(studentNum);
    }

    @GetMapping("/fetch/students/{gradeName}")
    public List<Student> fetchStudentsByGradeName(@PathVariable String gradeName) {
        return studentService.getStudentsByGradeName(gradeName);
    }

    @PostMapping("/students")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto dto) {
        Student savedStudent = studentService.saveStudent(dto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/update/students/{studentNum}/subject/{gradeName}")
    public ResponseEntity<?> updateStudentGrade(
            @PathVariable String gradeName,
            @PathVariable Integer studentNum
    ) {
        studentService.updateStudentGrade(gradeName, studentNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/students/{studentNum}")
    public ResponseEntity<?> deleteStudentByStudentNum(@PathVariable Integer studentNum) throws Exception {
        studentService.deleteStudentByStudentNum(studentNum);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
