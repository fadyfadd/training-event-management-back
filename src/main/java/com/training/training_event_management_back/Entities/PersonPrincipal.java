package com.training.training_event_management_back.Entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class PersonPrincipal implements UserDetails {

    private static final long serialVersionUId = 1L;

    private Person person;

    public Person getPerson() {
        return this.person;
    }

    public PersonPrincipal(Person person){
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (person.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority(person.getRole().getCurrentRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {

        return person.getPassword();
    }

    @Override
    public String getUsername() {

        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}