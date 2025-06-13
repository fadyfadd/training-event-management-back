package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor

@Table(name="Attendance")
public class Attendance extends BaseEntity {

    @Column(name = "EventId", nullable=false)
    private Long EventId;

    @Column(name = "StudentId", nullable = false)
    private Long StudentId;

    @Column(name = "StartDate", nullable = false)
    private LocalDate StartDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate EndDate;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
