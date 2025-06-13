package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Attendance extends BaseDto{
    private Long EventId;
    private Long StudentId;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private EventDto event;
    private StudentDto student;
}