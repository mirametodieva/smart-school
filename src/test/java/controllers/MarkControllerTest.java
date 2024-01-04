package controllers;

import com.example.smartschool.controllers.MarkController;
import com.example.smartschool.services.MarkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = MarkController.class)
public class MarkControllerTest {
    @MockBean
    private MarkService markService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void elementsMarkTest() throws Exception {
        when(markService.getMarkByTeacherNum(any()));
        mockMvc.perform(get("/fetch/marks/teachers"))
                .andExpect(status().isOk());
    }
}
