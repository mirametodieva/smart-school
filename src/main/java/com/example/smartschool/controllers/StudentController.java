package com.example.smartschool.controllers;

import com.example.smartschool.dto.StudentDto;
import com.example.smartschool.models.Student;
import com.example.smartschool.services.StudentService;
import lombok.RequiredArgsConstructor;
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

//    @GetMapping("/fetch/students/{studentId}")
//    public Student fetchStudentByStudentId(@PathVariable Long studentId) throws Exception {
//        return studentService.getStudentById(studentId);
//    }

    @GetMapping("/fetch/students/{studentNum}")
    public Student fetchStudentByStudentNum(@PathVariable Integer studentsNum) throws Exception {
        return studentService.getStudentByStudentNum(studentsNum);
    }

    @GetMapping("/fetch/students/{gradeName}")
    public List<Student> fetchStudentsByGradeName(@PathVariable String gradeName) {
        return studentService.getStudentsByGradeName(gradeName);
    }

//    @GetMapping("/fetch/students/{gradeId}")
//    public List<Student> fetchStudentsByGradeId(@PathVariable Long gradeId) {
//        return studentService.getStudentsByGradeId(gradeId);
//    }

    @PostMapping("/students")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto dto) {
        Student savedStudent = studentService.saveStudent(dto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/update/students/{studentNum}/subject/{gradeId}")
    public ResponseEntity<?> updateStudentGrade(
            @PathVariable Long gradeId,
            @PathVariable Integer studentNum
    ) {
        studentService.updateStudentGrade(gradeId, studentNum);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/students/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long studentId) throws Exception {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/students/{studentNum}")
    public ResponseEntity<?> deleteStudentByStudentNum(@PathVariable Integer studentNum) throws Exception {
        studentService.deleteStudentByStudentNum(studentNum);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
