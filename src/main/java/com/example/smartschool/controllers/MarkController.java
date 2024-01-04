package com.example.smartschool.controllers;

import com.example.smartschool.models.Mark;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.services.MarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MarkController {
    private final MarkService markService;

    @GetMapping("/fetch/marks/teachers/{teacherNum}")
    public List<Mark> fetchMarksByTeacherNum(@PathVariable Integer teacherNum) throws Exception {
        return markService.getMarkByTeacherNum(teacherNum);
    }

    @GetMapping("/fetch/marks/students/{studentNum}")
    public List<Mark> fetchMarksByStudentNum(@PathVariable Integer studentNum) throws Exception {
        return markService.getMarkByStudentNum(studentNum);
    }

    @DeleteMapping("/delete/marks/{markId}")
    public ResponseEntity<?> deleteMarkByMarkId(@PathVariable long markId){
        markService.deleteMarkByMarkId(markId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
