package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Person;
import com.training.training_event_management_back.Repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private PersonRepository repository;

    public PersonService(PersonRepository repository){
        this.repository = repository;
    }

    public List<Person> getAllPersons(){
        return repository.findAll();
    }
    public Person getPersonById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person with id: "+id+" not found"));
    }
    public Person createPerson(Person person) {
        return repository.save(person);
    }
    public Person updatePerson(Long id, Person updatedPerson){
        Person existing = getPersonById(id);
        existing.setUsername(updatedPerson.getUsername());
        existing.setPassword(updatedPerson.getPassword());
        existing.setEmail(updatedPerson.getEmail());
        existing.setFirstName(updatedPerson.getFirstName());
        existing.setLastName(updatedPerson.getLastName());
        return repository.save(existing);
    }
    public void deletePerson(Long id) {
        repository.deleteById(id);
    }

}