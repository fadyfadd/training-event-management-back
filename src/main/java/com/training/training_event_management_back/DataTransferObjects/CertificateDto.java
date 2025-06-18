package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

@Data

public class CertificateDto extends BaseDto{
    private Long courseId;
    private Long eventId;
    private String eventTitle;
    private EventDto event;
}