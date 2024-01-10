package controllers;


import com.example.smartschool.controllers.TeacherController;
import com.example.smartschool.dto.TeacherDto;
import com.example.smartschool.models.Teacher;
import com.example.smartschool.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    @Test
    public void testFetchTeachers() throws Exception {
        List<Teacher> teachers = Collections.singletonList(new Teacher());

        Mockito.when(teacherService.getAllTeachers()).thenReturn(teachers);

        mockMvc.perform(MockMvcRequestBuilders.get("/fetch/teachers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testFetchTeacherByNum() throws Exception {
        int teacherNum = 777;
        Teacher teacher = new Teacher();

        Mockito.when(teacherService.getTeacherByTeacherNum(Mockito.eq(teacherNum))).thenReturn(teacher);

        mockMvc.perform(MockMvcRequestBuilders.get("/fetch/teachers/{teacherNum}", teacherNum)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap());
    }

    @Test
    public void testFetchTeachersBySubjectName() throws Exception {
        String subjectName = "Math";
        List<Teacher> teachers = Collections.singletonList(new Teacher());

        Mockito.when(teacherService.getTeachersBySubjectName(Mockito.eq(subjectName))).thenReturn(teachers);

        mockMvc.perform(MockMvcRequestBuilders.get("/fetch/teachers/{subjectName}", subjectName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testSaveTeacher() throws Exception {
        TeacherDto teacherDto = new TeacherDto("Nina", "Dobrev", 111, "master's degree at XAMK");

        Mockito.when(teacherService.saveTeacher(Mockito.any(TeacherDto.class))).thenReturn(new Teacher());

        mockMvc.perform(MockMvcRequestBuilders.post("/save/teachers")
                        .content(asJsonString(teacherDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap());
    }

    @Test
    public void testUpdateTeacherSubject() throws Exception {
        int teacherNum = 111;
        String subjectName = "Math";

        mockMvc.perform(MockMvcRequestBuilders.put("/update/teachers/{teacherNum}/subject/{subjectName}", teacherNum, subjectName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testUpdateTeacherAddGrade() throws Exception {
        int teacherNum = 111;
        String gradeName = "Ladybug";

        mockMvc.perform(MockMvcRequestBuilders.put("/update/teachers/{teacherNum}/grades/{gradeName}/add", teacherNum, gradeName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testUpdateTeacherDeleteGrade() throws Exception {
        int teacherNum = 111;
        String gradeName = "Ladybug";

        mockMvc.perform(MockMvcRequestBuilders.put("/update/teachers/{teacherNum}/grades/{gradeName}/delete", teacherNum, gradeName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteTeacherByTeacherNum() throws Exception {
        int teacherNum = 111;

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/teachers/{teacherNum}", teacherNum)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
