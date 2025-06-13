package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Attendance;
import com.training.training_event_management_back.Repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    private AttendanceRepository repository;
    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }
    public List<Attendance> getAllAttendances(){
        return repository.findAll();
    }
    public Attendance getAttendanceById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: "+id));
    }
    public Attendance createAttendance(Attendance attendance){
        return repository.save(attendance);
    }
    public Attendance updateAttendance(Long id, Attendance updatedAttendance){
        Attendance existing = getAttendanceById(id);
        existing.setStartDate(updatedAttendance.getStartDate());
        existing.setEndDate(updatedAttendance.getEndDate());
        existing.setEvent(updatedAttendance.getEvent());
        existing.setStudent(updatedAttendance.getStudent());
        return repository.save(existing);
    }
    public void deleteAttendance(Long id){
        repository.deleteById(id);
    }
}