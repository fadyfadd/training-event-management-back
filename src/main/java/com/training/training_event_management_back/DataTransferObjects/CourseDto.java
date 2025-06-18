package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CourseDto extends BaseDto {
    private String description;
    private Long nbofHours;
    private Long minAttendance;
    private List<Long> eventIds;
    private Set<Long> teacherIds;
}