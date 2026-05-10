package com.lab7;

import com.lab7.model.Student;
import com.lab7.repository.StudentRepository;
import com.lab7.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);
        student.setName("Nikita");
        student.setAge(19);
    }

    @Test
    void testSaveStudent() {

        when(repository.save(any(Student.class)))
                .thenReturn(student);

        Student savedStudent = service.saveStudent(student);

        assertNotNull(savedStudent);
        assertEquals("Nikita", savedStudent.getName());
        assertEquals(19, savedStudent.getAge());

        verify(repository, times(1))
                .save(student);
    }

    @Test
    void testGetAllStudents() {

        when(repository.findAll())
                .thenReturn(List.of(student));

        List<Student> students =
                service.getAllStudents();

        assertEquals(1, students.size());

        verify(repository, times(1))
                .findAll();
    }

    @Test
    void testGetStudentById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(student));

        Student foundStudent =
                service.getStudentById(1L);

        assertNotNull(foundStudent);
        assertEquals("Nikita",
                foundStudent.getName());

        verify(repository, times(1))
                .findById(1L);
    }

    @Test
    void testDeleteStudent() {

        doNothing()
                .when(repository)
                .deleteById(1L);

        service.deleteStudent(1L);

        verify(repository, times(1))
                .deleteById(1L);
    }
}