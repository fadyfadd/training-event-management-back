package com.training.training_event_management_back.Enums;

public enum Role {
    ADMIN("ROLE_ADMIN"), STUDENT("ROLE_STUDENT"), TEACHER("ROLE_TEACHER");

    String currentRole;

    Role(String role){
        currentRole = role;
    }

    public String getCurrentRole(){
        return currentRole;
    }

}