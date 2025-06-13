package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="Events")
public class Event extends BaseEntity {

    @Column(name = "title", nullable=false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "maxStudent", nullable = false)
    private Long maxStudent;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private Course course;

    @ManyToMany(mappedBy = "events")
    private Set<Student> students;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Certificate> certificateList;
}
