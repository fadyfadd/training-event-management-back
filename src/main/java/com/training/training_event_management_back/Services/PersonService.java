package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.PersonDto;
import com.training.training_event_management_back.Entities.Person;
import com.training.training_event_management_back.Repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PersonDto getPersonById(Long id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person with id: " + id + " not found"));
        return toDto(person);
    }

    public PersonDto createPerson(PersonDto dto) {
        Person person = new Person();
        copyFromDto(dto, person);
        Person saved = repository.save(person);
        return toDto(saved);
    }

    public PersonDto updatePerson(Long id, PersonDto dto) {
        Person existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person with id: " + id + " not found"));
        copyFromDto(dto, existing);
        Person updated = repository.save(existing);
        return toDto(updated);
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }

    private PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setUsername(person.getUsername());
        dto.setPassword(person.getPassword());
        dto.setEmail(person.getEmail());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        return dto;
    }

    private void copyFromDto(PersonDto dto, Person person) {
        person.setId(dto.getId());
        person.setUsername(dto.getUsername());
        person.setPassword(passwordEncoder.encode(dto.getPassword()));
        person.setEmail(dto.getEmail());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
    }

    //public boolean authenticate(String rawPassword, String hashedPasswordFromDb) {
    //    return passwordEncoder.matches(rawPassword, hashedPasswordFromDb);
    //} for authenticating later
}
