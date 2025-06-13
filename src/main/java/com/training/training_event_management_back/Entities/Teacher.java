package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="Teachers")
public class Teacher extends BaseEntity {

    @Column(name = "PersonId", nullable=false)
    private Long person_id;

    @OneToMany(mappedBy = "teacher")
    private List<Event> eventList;

    @ManyToMany
    @JoinTable (name = "course_teacher",
                joinColumns = @JoinColumn (name = "teacher_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id")
                )
    private Set<Course> courses;
}
