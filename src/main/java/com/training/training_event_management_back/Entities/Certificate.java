package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Certificates")
public class Certificate extends BaseEntity {

    @Column(name = "CourseId", nullable=false)
    private Long course_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
