package io.reflectoring.ActiveLogin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Long tkmId;
    private String password;
}

