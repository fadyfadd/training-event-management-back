package com.training.training_event_management_back.Controllers;

import com.training.training_event_management_back.DataTransferObjects.LoginRequest;
import com.training.training_event_management_back.DataTransferObjects.PersonDto;
import com.training.training_event_management_back.Entities.Person;
import com.training.training_event_management_back.Services.PersonService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/persons")
@RestController
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllPersons(){
        List<PersonDto> output = personService.getAllPersons();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable Long id) {
        try{
            PersonDto person = personService.getPersonById(id);
            return ResponseEntity.ok(person);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto person) {
        PersonDto created = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto person) {
        try{
            PersonDto updated = personService.updatePerson(id, person);
            return ResponseEntity.ok(updated);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return personService.login(loginRequest);
    }
}