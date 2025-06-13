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

    @Column(name = "Title", nullable=false)
    private String Title;

    @Column(name = "Description", nullable = false)
    private String Description;

    @Column(name = "StartDate", nullable = false)
    private LocalDate StartDate;

    @Column(name = "EndDate", nullable = false)
    private LocalDate EndDate;

    @Column(name = "MaxStudent", nullable = false)
    private Long MaxStudent;

    @ManyToOne
    @JoinColumn(name = "Teacher_Id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "Course_Id")
    private Course course;

    @ManyToMany(mappedBy = "events")
    private Set<Student> students;

    @OneToMany(mappedBy = "event")
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "event")
    private List<Certificate> certificateList;
}
