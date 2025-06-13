package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="Students")
public class Student extends BaseEntity {

    @Column(name = "PersonId", nullable=false)
    private Long person_id;

    @ManyToMany
    @JoinTable(name = "event_students",
                joinColumns = @JoinColumn(name = "student_id"),
                inverseJoinColumns = @JoinColumn(name = "event_id")
                )
    private Set<Event> events;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendanceList;
}
