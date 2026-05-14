package com.lab7.service;

import com.lab7.exception.StudentNotFoundException;
import com.lab7.model.Student;
import com.lab7.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student getStudentById(Long id) {
        return repository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student saveStudent(Student student) {
        return repository.save(Objects.requireNonNull(student));
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Objects.requireNonNull(updatedStudent);
        Student student = getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        return repository.save(student);
    }

    public void deleteStudent(Long id) {
        repository.deleteById(Objects.requireNonNull(id));
    }
}