package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.TeacherDto;
import com.training.training_event_management_back.Entities.Course;
import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Entities.Teacher;
import com.training.training_event_management_back.Repositories.CourseRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import com.training.training_event_management_back.Repositories.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CourseRepository courseRepository;

    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TeacherDto getTeacherById(Long id) {
        return toDto(getEntityById(id));
    }

    public TeacherDto createTeacher(TeacherDto dto) {
        Teacher teacher = new Teacher();
        copyFromDto(dto, teacher);
        return toDto(teacherRepository.save(teacher));
    }

    public TeacherDto updateTeacher(Long id, TeacherDto dto) {
        Teacher existing = getEntityById(id);
        copyFromDto(dto, existing);
        return toDto(teacherRepository.save(existing));
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    private TeacherDto toDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setPersonId(teacher.getPersonId());

        if (teacher.getEventList() != null) {
            dto.setEventIds(
                    teacher.getEventList().stream().map(Event::getId).collect(Collectors.toList()));
        }

        if (teacher.getCourses() != null) {
            dto.setCourseIds(
                    teacher.getCourses().stream().map(Course::getId).collect(Collectors.toSet()));
        }

        return dto;
    }

    private void copyFromDto(TeacherDto dto, Teacher teacher) {
        teacher.setId(dto.getId());
        teacher.setPersonId(dto.getPersonId());

        if (dto.getEventIds() != null) {
            List<Event> events = eventRepository.findAllById(dto.getEventIds());
            teacher.setEventList(events);
        }

        if (dto.getCourseIds() != null) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(dto.getCourseIds()));
            teacher.setCourses(courses);
        }
    }

    private Teacher getEntityById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher with id: " + id + " not found."));
    }
}
