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

    @Column(name = "personId", nullable=false)
    private Long personId;

    @ManyToMany
    @JoinTable(name = "event_students",
                joinColumns = @JoinColumn(name = "studentId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "eventId", referencedColumnName = "id")
                )
    private Set<Event> events;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList;
}
