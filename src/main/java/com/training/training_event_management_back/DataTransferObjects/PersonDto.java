package com.training.training_event_management_back.DataTransferObjects;

import lombok.Data;

@Data
public class PersonDto extends BaseDto{
    private String Username;
    private String Email;
    private String Password;
    private String FirstName;
    private String LastName;
}