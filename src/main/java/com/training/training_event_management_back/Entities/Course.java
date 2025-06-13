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

    @Column(name = "Description", nullable=false)
    private String Description;

    @Column(name = "NbofHours", nullable = false)
    private Long NbofHours;

    @Column(name = "MinAttendance", nullable = false)
    private String MinAttendance;

    @OneToMany(mappedBy = "course")
    private List<Event> eventList;

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers;
}
