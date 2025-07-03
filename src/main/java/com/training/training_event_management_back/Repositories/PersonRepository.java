package com.training.training_event_management_back.Repositories;

import com.training.training_event_management_back.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername (String username);
}