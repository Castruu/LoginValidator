package com.login.validator.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotBlank(message = "Username field can't be blank")
    @Size(min = 3, message = "Username has to contain at least 3 letters")
    private String username;
    @NotBlank(message = "Email field can't be blank")
    @Email(message = "Email field must be a valid email")
    private String email;
    @Size(min = 6, message = "Password has to contain at least 6 letters")
    private String password;

}
