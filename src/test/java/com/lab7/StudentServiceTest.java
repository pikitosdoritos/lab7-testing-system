package com.lab7;

import com.lab7.exception.StudentNotFoundException;
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
        student = new Student("Nikita", 19);
        student.setId(1L);
    }

    @Test
    void testSaveStudent() {
        when(repository.save(any(Student.class))).thenReturn(student);

        Student saved = service.saveStudent(student);

        assertNotNull(saved);
        assertEquals("Nikita", saved.getName());
        assertEquals(19, saved.getAge());
        verify(repository, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        when(repository.findAll()).thenReturn(List.of(student));

        List<Student> students = service.getAllStudents();

        assertEquals(1, students.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetStudentById() {
        when(repository.findById(1L)).thenReturn(Optional.of(student));

        Student found = service.getStudentById(1L);

        assertNotNull(found);
        assertEquals("Nikita", found.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentByIdThrowsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> service.getStudentById(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void testUpdateStudent() {
        Student updated = new Student("Updated", 25);
        when(repository.findById(1L)).thenReturn(Optional.of(student));
        when(repository.save(any(Student.class))).thenReturn(student);

        Student result = service.updateStudent(1L, updated);

        assertNotNull(result);
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(student);
    }

    @Test
    void testUpdateStudentThrowsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class,
                () -> service.updateStudent(99L, new Student("X", 20)));
    }

    @Test
    void testDeleteStudent() {
        doNothing().when(repository).deleteById(1L);

        service.deleteStudent(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}