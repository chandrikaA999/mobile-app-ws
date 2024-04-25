package com.appsdeveloperblog.app.ws.ui.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetailsRequestModel
{

    @NotNull(message = "First name cant be null")
    private String firstName;
    @NotNull(message = "Last name cant be null")
    private String lastName;
    @NotNull(message = "email cant be null")
    @Email
    private String email;
    @NotNull(message = "password cant be null")
    @Size(min=8,max=16,message="passwords are >8 and <16")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
