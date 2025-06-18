package com.training.training_event_management_back.Controllers;

import com.training.training_event_management_back.DataTransferObjects.AttendanceDto;
import com.training.training_event_management_back.Entities.Attendance;
import com.training.training_event_management_back.Services.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/attendance")
@RestController
public class AttendanceController {
    private final AttendanceService attendanceService;
    public AttendanceController (AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAllAttendances() {
        List<AttendanceDto> output = attendanceService.getAllAttendances();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendancebyId(@PathVariable Long id) {
        try {
            AttendanceDto attendance = attendanceService.getAttendanceById(id);
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendance) {
        AttendanceDto created = attendanceService.createAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDto attendance) {
        try {
            AttendanceDto updated = attendanceService.updateAttendance(id, attendance);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
