package com.lab7;

import com.lab7.model.Student;
import com.lab7.repository.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void testSaveAndFindById() {
        Student saved = repository.save(new Student("Alice", 20));

        assertNotNull(saved.getId());
        Optional<Student> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
        assertEquals(20, found.get().getAge());
    }

    @Test
    void testFindAll() {
        repository.save(new Student("Alice", 20));
        repository.save(new Student("Bob", 22));

        List<Student> students = repository.findAll();
        assertEquals(2, students.size());
    }

    @Test
    void testDeleteById() {
        Student saved = repository.save(new Student("Alice", 20));
        Long id = saved.getId();

        repository.deleteById(id);

        assertFalse(repository.findById(id).isPresent());
    }

    @Test
    void testUpdateStudent() {
        Student saved = repository.save(new Student("Alice", 20));
        saved.setName("Alice Updated");
        saved.setAge(25);
        Student updated = repository.save(saved);

        assertEquals("Alice Updated", updated.getName());
        assertEquals(25, updated.getAge());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Student> result = repository.findById(999L);
        assertFalse(result.isPresent());
    }
}
