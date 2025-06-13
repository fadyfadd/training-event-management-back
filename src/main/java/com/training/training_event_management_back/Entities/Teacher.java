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

    @Column(name = "personId", nullable=false)
    private Long personId;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Event> eventList;

    @ManyToMany
    @JoinTable (name = "course_teacher",
                joinColumns = @JoinColumn (name = "teacherId", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "courseId", referencedColumnName = "id")
                )
    private Set<Course> courses;
}
