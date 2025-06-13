package com.training.training_event_management_back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="Persons")
public class Person extends BaseEntity {

    @Column(name = "Username", nullable=false)
    private String Username;

    @Column(name = "Email", nullable = false)
    private String Email;

    @Column(name = "Password", nullable = false)
    private String Password;

    @Column(name = "FirstName", nullable = false)
    private String FirstName;

    @Column(name = "LastName", nullable = false)
    private String LastName;
}
