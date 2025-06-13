package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Student;
import com.training.training_event_management_back.Repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    public List<Student> getAllStudents(){
        return repository.findAll();
    }
    public Student getStudentById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found with id: "+id));
    }
    public Student createStudent(Student student){
        return repository.save(student);
    }
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);
        existing.setPersonId(updatedStudent.getPersonId());
        existing.setEvents(updatedStudent.getEvents());
        existing.setAttendanceList(updatedStudent.getAttendanceList());
        return repository.save(existing);
    }
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}