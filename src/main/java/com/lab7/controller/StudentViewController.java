package com.lab7.controller;

import com.lab7.model.Student;
import com.lab7.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students-view")
public class StudentViewController {

    private final StudentService service;

    public StudentViewController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "list";
    }

    @GetMapping("/{id}")
    public String studentDetail(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "detail";
    }

    @GetMapping("/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @GetMapping("/{id}/edit")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "form";
    }

    @PostMapping
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) return "form";
        service.saveStudent(student);
        return "redirect:/students-view";
    }

    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute Student student,
                                BindingResult result) {
        if (result.hasErrors()) return "form";
        service.updateStudent(id, student);
        return "redirect:/students-view";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "redirect:/students-view";
    }
}
