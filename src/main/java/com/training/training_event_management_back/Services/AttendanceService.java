package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.AttendanceDto;
import com.training.training_event_management_back.Entities.Attendance;
import com.training.training_event_management_back.Repositories.AttendanceRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import com.training.training_event_management_back.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<AttendanceDto> getAllAttendances() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AttendanceDto getAttendanceById(Long id) {
        Attendance attendance = getAttendanceEntityById(id);
        return toDto(attendance);
    }

    public AttendanceDto createAttendance(AttendanceDto dto) {
        Attendance attendance = new Attendance();
        copyFromDto(dto, attendance);
        Attendance saved = repository.save(attendance);
        return toDto(saved);
    }

    public AttendanceDto updateAttendance(Long id, AttendanceDto dto) {
        Attendance existing = getAttendanceEntityById(id);
        copyFromDto(dto, existing);
        Attendance updated = repository.save(existing);
        return toDto(updated);
    }

    public void deleteAttendance(Long id) {
        repository.deleteById(id);
    }

    private Attendance getAttendanceEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
    }

    private AttendanceDto toDto(Attendance attendance) {
        AttendanceDto dto = new AttendanceDto();
        dto.setId(attendance.getId());
        dto.setStartDate(attendance.getStartDate());
        dto.setEndDate(attendance.getEndDate());

        if (attendance.getEvent() != null) {
            dto.setEventId(attendance.getEvent().getId());
            dto.setEventTitle(attendance.getEvent().getTitle());
        }

        if (attendance.getStudent() != null) {
            dto.setStudentId(attendance.getStudent().getId());
            dto.setStudentFullName(attendance.getStudent().getPersonId().getClass().getName());
        }

        return dto;
    }

    private void copyFromDto(AttendanceDto dto, Attendance attendance) {
        attendance.setId(dto.getId());
        attendance.setStartDate(dto.getStartDate());
        attendance.setEndDate(dto.getEndDate());

        if (dto.getEventId() != null) {
            attendance.setEvent(eventRepository.findById(dto.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found")));
        }

        if (dto.getStudentId() != null) {
            attendance.setStudent(studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found")));
        }
    }
}
