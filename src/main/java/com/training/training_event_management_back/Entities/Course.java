package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="Courses")
public class Course extends BaseEntity {

    @Column(name = "description", nullable=false)
    private String description;

    @Column(name = "nbofHours", nullable = false)
    private Long nbofHours;

    @Column(name = "minAttendance", nullable = false)
    private String minAttendance;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Event> eventList;

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers;
}
