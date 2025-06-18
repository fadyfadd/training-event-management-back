package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.EventDto;
import com.training.training_event_management_back.Entities.Course;
import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Entities.Teacher;
import com.training.training_event_management_back.Repositories.CourseRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import com.training.training_event_management_back.Repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public EventService(EventRepository eventRepository,
                        TeacherRepository teacherRepository,
                        CourseRepository courseRepository) {
        this.eventRepository = eventRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EventDto getEventById(Long id) {
        Event event = getEventEntityById(id);
        return toDto(event);
    }

    public EventDto createEvent(EventDto dto) {
        Event event = new Event();
        copyFromDto(dto, event);
        Event saved = eventRepository.save(event);
        return toDto(saved);
    }

    public EventDto updateEvent(Long id, EventDto dto) {
        Event existing = getEventEntityById(id);
        copyFromDto(dto, existing);
        Event updated = eventRepository.save(existing);
        return toDto(updated);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private Event getEventEntityById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with ID " + id + " not found"));
    }

    private EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setStartDate(event.getStartDate());
        dto.setEndDate(event.getEndDate());
        dto.setMaxStudent(event.getMaxStudent());

        if (event.getTeacher() != null) {
            dto.setTeacherId(event.getTeacher().getId());
            //dto.setTeacherName(event.getTeacher().getName());
        }

        if (event.getCourse() != null) {
            dto.setCourseId(event.getCourse().getId());
            dto.setCourseName(event.getCourse().getDescription());
        }

        return dto;
    }

    private void copyFromDto(EventDto dto, Event event) {
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartDate(dto.getStartDate());
        event.setEndDate(dto.getEndDate());
        event.setMaxStudent(dto.getMaxStudent());

        if (dto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            event.setTeacher(teacher);
        }

        if (dto.getCourseId() != null) {
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            event.setCourse(course);
        }
    }
}
