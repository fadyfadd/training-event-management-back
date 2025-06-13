package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Teacher;
import com.training.training_event_management_back.Repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TeacherService {

    private TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Teacher with id: " + id +" not found."));
    }

    public Teacher createTeacher(Teacher teacher) {
        return repository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existing = getTeacherById(id);
        existing.setPersonId(updatedTeacher.getPersonId());
        existing.setCourses(updatedTeacher.getCourses());
        existing.setEventList(updatedTeacher.getEventList());
        return repository.save(existing);
    }

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }
}