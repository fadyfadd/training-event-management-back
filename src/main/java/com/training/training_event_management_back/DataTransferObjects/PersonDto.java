package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

@Data
public class PersonDto extends BaseDto{
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}