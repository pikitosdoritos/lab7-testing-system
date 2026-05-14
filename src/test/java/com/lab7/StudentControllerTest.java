package com.lab7;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab7.controller.StudentController;
import com.lab7.exception.StudentNotFoundException;
import com.lab7.model.Student;
import com.lab7.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Nikita", 19);
        student.setId(1L);
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(service.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Nikita"))
                .andExpect(jsonPath("$[0].age").value(19));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(service.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nikita"))
                .andExpect(jsonPath("$.age").value(19));
    }

    @Test
    void testGetStudentByIdNotFound() throws Exception {
        when(service.getStudentById(99L)).thenThrow(new StudentNotFoundException(99L));

        mockMvc.perform(get("/students/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateStudent() throws Exception {
        when(service.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nikita"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(service.updateStudent(eq(1L), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Nikita"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(service).deleteStudent(1L);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully"));
    }
}
