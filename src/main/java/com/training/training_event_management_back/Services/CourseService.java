package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.CourseDto;
import com.training.training_event_management_back.Entities.Course;
import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Entities.Teacher;
import com.training.training_event_management_back.Repositories.CourseRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import com.training.training_event_management_back.Repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EventRepository eventRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, EventRepository eventRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.eventRepository = eventRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CourseDto getCourseById(Long id) {
        return toDto(getEntityById(id));
    }

    public CourseDto createCourse(CourseDto dto) {
        Course course = new Course();
        copyFromDto(dto, course);
        return toDto(courseRepository.save(course));
    }

    public CourseDto updateCourse(Long id, CourseDto dto) {
        Course existing = getEntityById(id);
        copyFromDto(dto, existing);
        return toDto(courseRepository.save(existing));
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private Course getEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    private CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setDescription(course.getDescription());
        dto.setNbofHours(course.getNbofHours());
        dto.setMinAttendance(course.getMinAttendance());

        if (course.getEventList() != null) {
            dto.setEventIds(course.getEventList().stream()
                    .map(Event::getId)
                    .collect(Collectors.toList()));
        }

        if (course.getTeachers() != null) {
            dto.setTeacherIds(course.getTeachers().stream()
                    .map(Teacher::getId)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

    private void copyFromDto(CourseDto dto, Course course) {
        course.setDescription(dto.getDescription());
        course.setNbofHours(dto.getNbofHours());
        course.setMinAttendance(dto.getMinAttendance());

        if (dto.getEventIds() != null) {
            List<Event> events = eventRepository.findAllById(dto.getEventIds());
            course.setEventList(events);
        }

        if (dto.getTeacherIds() != null) {
            Set<Teacher> teachers = new HashSet<>(teacherRepository.findAllById(dto.getTeacherIds()));
            course.setTeachers(teachers);
        }
    }
}
