package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Course;
import com.training.training_event_management_back.Repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository repository;
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }
    public List<Course> getAllCourses(){
        return repository.findAll();
    }
    public Course getCourseById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: "+id));
    }
    public Course createCourse(Course course){
        return repository.save(course);
    }
    public Course updateCourse(Long id, Course updatedCourse){
        Course existing = getCourseById(id);
        existing.setDescription(updatedCourse.getDescription());
        existing.setNbofHours(updatedCourse.getNbofHours());
        existing.setMinAttendance(updatedCourse.getMinAttendance());
        existing.setEventList(updatedCourse.getEventList());
        existing.setTeachers(updatedCourse.getTeachers());
        return repository.save(existing);
    }
    public void deleteCourse(Long id){
        repository.deleteById(id);
    }
}