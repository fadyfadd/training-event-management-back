package com.training.training_event_management_back.Repositories;

import com.training.training_event_management_back.Entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}