package com.training.training_event_management_back.Controllers;

import com.training.training_event_management_back.DataTransferObjects.TeacherDto;
import com.training.training_event_management_back.Entities.Teacher;
import com.training.training_event_management_back.Services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/teachers")
@RestController
public class TeacherController {
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> output = teacherService.getAllTeachers();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherbyId(@PathVariable Long id) {
        try {
            TeacherDto teacher = teacherService.getTeacherById(id);
            return ResponseEntity.ok(teacher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacher) {
        TeacherDto created = teacherService.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacher) {
        try {
            TeacherDto updated = teacherService.updateTeacher(id, teacher);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
