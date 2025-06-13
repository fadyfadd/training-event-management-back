package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Certificates")
public class Certificate extends BaseEntity {

    @Column(name = "courseId", nullable=false)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    private Event event;
}
