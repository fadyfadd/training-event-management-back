package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDto extends BaseDto{
    private Long eventId;
    private Long studentId;
    private LocalDate startDate;
    private LocalDate endDate;
    private EventDto event;
    private StudentDto student;
    private String eventTitle;
    private String studentFullName;
}