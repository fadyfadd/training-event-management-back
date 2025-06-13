package com.training.training_event_management_back.Controllers;

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
    private PersonService personService;
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> output = personService.getAllPersons();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        try{
            Person person = personService.getPersonById(id);
            return ResponseEntity.ok(person);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person created = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        try{
            Person updated = personService.updatePerson(id, person);
            return ResponseEntity.ok(updated);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}