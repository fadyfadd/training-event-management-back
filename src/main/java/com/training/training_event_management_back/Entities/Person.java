package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.training.training_event_management_back.Enums.Role;

@Entity
@Data
@NoArgsConstructor
@Table(name="Persons")
public class Person extends BaseEntity {

    @Column(name = "username", nullable=false)
    private String username;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    Role role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;
}
