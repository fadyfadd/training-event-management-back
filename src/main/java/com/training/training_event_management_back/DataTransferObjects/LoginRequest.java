package com.training.training_event_management_back.DataTransferObjects;

import com.training.training_event_management_back.Enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private Role role;
}