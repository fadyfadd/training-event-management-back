package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

import java.time.LocalDate;

@Data

public class EventDto extends BaseDto {
    private String Title;
    private String Description;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private Long MaxStudent;
}