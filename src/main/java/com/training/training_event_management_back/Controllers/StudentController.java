package com.training.training_event_management_back.Controllers;

import com.training.training_event_management_back.Repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class StudentController {

    @GetMapping("/students")
    public ResponseEntity<List<String>> getStudents() {
        List<String> output = new ArrayList<>();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<String>> getStudentbyId(Long id) {
        List<String> output = new ArrayList<>();
        return ResponseEntity.ok(output);
    }
}
