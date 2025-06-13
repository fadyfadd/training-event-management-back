package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.time.LocalDate;

@Data

public class EventDto extends BaseDto {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long maxStudent;
}