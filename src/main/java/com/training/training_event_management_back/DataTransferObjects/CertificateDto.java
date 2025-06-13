package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

@Data

public class CertificateDto extends BaseDto{
    private Long course_id;
    private EventDto event;
}