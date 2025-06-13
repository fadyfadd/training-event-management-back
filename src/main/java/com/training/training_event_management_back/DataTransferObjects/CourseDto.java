package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

@Data
public class CourseDto extends BaseDto {
    private String description;
    private Long nbofHours;
    private Long minAttendance;
}