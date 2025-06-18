package com.training.training_event_management_back.Controllers;

import com.training.training_event_management_back.DataTransferObjects.StudentDto;
import com.training.training_event_management_back.Entities.Student;
import com.training.training_event_management_back.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/students")
@RestController
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> output = studentService.getAllStudents();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentbyId(@PathVariable Long id) {
       try {
           StudentDto student = studentService.getStudentById(id);
           return ResponseEntity.ok(student);
       } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student) {
        StudentDto created = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto student) {
        try {
            StudentDto updated = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
