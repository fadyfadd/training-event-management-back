package com.training.training_event_management_back.DataTransferObjects;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data

public class BaseDto {
    private Long id;
}
