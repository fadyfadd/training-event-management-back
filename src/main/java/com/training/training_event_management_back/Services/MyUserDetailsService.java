package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Person;
import com.training.training_event_management_back.Entities.PersonPrincipal;
import com.training.training_event_management_back.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

     @Autowired
    private PersonRepository personRepository;

     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Person person = personRepository.findByUsername(username);

         if (person == null) {
             throw new UsernameNotFoundException("User 404");
         }
         return new PersonPrincipal(person);
     }
}