package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class StudentDto extends BaseDto{
    private Long personId;
    private Set<Long> eventIds;
    private List<Long> attendanceIds;
}