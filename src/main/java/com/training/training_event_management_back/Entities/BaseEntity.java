package com.training.training_event_management_back.Entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @Id
    private Long id;
}
