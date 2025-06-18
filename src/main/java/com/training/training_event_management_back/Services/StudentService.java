package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.StudentDto;
import com.training.training_event_management_back.Entities.Attendance;
import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Entities.Student;
import com.training.training_event_management_back.Repositories.AttendanceRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import com.training.training_event_management_back.Repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;
    private final AttendanceRepository attendanceRepository;

    public StudentService(StudentRepository studentRepository,
                          EventRepository eventRepository,
                          AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.eventRepository = eventRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long id) {
        return toDto(getEntityById(id));
    }

    public StudentDto createStudent(StudentDto dto) {
        Student student = new Student();
        copyFromDto(dto, student);
        return toDto(studentRepository.save(student));
    }

    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student existing = getEntityById(id);
        copyFromDto(dto, existing);
        return toDto(studentRepository.save(existing));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setPersonId(student.getPersonId());

        if (student.getEvents() != null) {
            dto.setEventIds(
                    student.getEvents().stream().map(Event::getId).collect(Collectors.toSet()));
        }

        if (student.getAttendanceList() != null) {
            dto.setAttendanceIds(
                    student.getAttendanceList().stream().map(Attendance::getId).collect(Collectors.toList()));
        }

        return dto;
    }

    private void copyFromDto(StudentDto dto, Student student) {
        student.setPersonId(dto.getPersonId());

        if (dto.getEventIds() != null) {
            Set<Event> events = new HashSet<>(eventRepository.findAllById(dto.getEventIds()));
            student.setEvents(events);
        }

        if (dto.getAttendanceIds() != null) {
            List<Attendance> attendanceList = attendanceRepository.findAllById(dto.getAttendanceIds());
            student.setAttendanceList(attendanceList);
        }
    }

    private Student getEntityById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
