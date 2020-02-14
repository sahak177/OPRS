package com.example.oprs.pojo;


import com.example.oprs.exception.InValidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Officer {


    private String socialNumber;

    private String email;

    private String firstName;

    private String lastName;

    private String password;
    private String role;

    public Officer() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void validateInput() throws InValidInputException {

        if (password.length() < 8 || password.length() > 16) {
            throw new InValidInputException("invalid password");
        }

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern patternEmail = Pattern.compile(regex);
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.matches()) {
            throw new InValidInputException("invalid email");
        }
        String regexName = "[A-Z][a-z]*";
        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherFName = patternName.matcher(firstName);

        if (!matcherFName.matches() || firstName.length() < 2 || firstName.length() > 20) {
            throw new InValidInputException("invalid First Name");
        }

        Matcher matcherLName = patternName.matcher(lastName);
        if (!matcherLName.matches() || lastName.length() < 2 || lastName.length() > 20) {
            throw new InValidInputException("invalid Last Name");
        }

        Pattern patternNumber = Pattern.compile("\\d{9}");
        Matcher matcherNumber = patternNumber.matcher(socialNumber);

        if (!matcherNumber.matches()) {
            throw new InValidInputException("invalid Social Number");
        }
        if (!role.equals("ROLE_OFFICER") && !role.equals("ROLE_OVIR_OFFICER")) {
            throw new InValidInputException("invalid position");
        }
    }
}
