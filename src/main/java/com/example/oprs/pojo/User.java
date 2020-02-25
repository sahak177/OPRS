package com.example.oprs.pojo;

import com.example.oprs.annotation.CheckSecurityCode;
import com.example.oprs.annotation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class User {


    private Long id;

    @Pattern(regexp = "\\d{9}", message = "Social Security Number size must be 9 And only numbers")
    @NotNull(message = "Social Security Number can't be null")
    private String socialSecurityNumber;

    @UniqueEmail
    @Email
    @NotNull(message = "Email can't be null")
    private String email;

    @Pattern(regexp = "[A-Za-z0-9]{8,}", message = "Password must have 8-or more characters")
    @NotNull(message = "Password can't be null")
    private String password;

    @Pattern(regexp = "[A-Z][a-z]*", message = "First Name must have one capital letter and then a small letters")
    @Size(min = 3, max = 50, message = "Name characters size must be between 3 and 50")
    @NotNull(message = "First Name can't be null")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]*", message = "Last Name must have one capital letter and then a small letters")
    @Size(min = 3, max = 50, message = "Name characters size must be between 3 and 50")
    @NotNull(message = "Last Name can't be null")
    private String lastName;

    private List<String> Tokens;

    private List<Role> roles;

    @CheckSecurityCode

    private String securityCode;

    public User() {
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getTokens() {
        return Tokens;
    }

    public void setTokens(List<String> tokens) {
        Tokens = tokens;
    }
}
