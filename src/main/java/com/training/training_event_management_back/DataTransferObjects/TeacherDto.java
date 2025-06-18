package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data

public class TeacherDto extends BaseDto{
    private Long personId;
    private List<Long> eventIds;
    private Set<Long> courseIds;
}