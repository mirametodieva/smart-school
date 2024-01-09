package controllers;

import com.example.smartschool.SmartSchoolApplication;
import com.example.smartschool.controllers.StudentController;
import com.example.smartschool.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(StudentController.class)
@ContextConfiguration(classes={SmartSchoolApplication.class})
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void StudentsTest() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/fetch/students"))
                .andExpect(status().isOk());
    }
}
