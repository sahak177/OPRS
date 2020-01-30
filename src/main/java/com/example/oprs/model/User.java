package com.example.oprs.model;

import com.example.oprs.exception.InValidInput;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {


    private Long id;

    private String socialNumber;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private List<Role> roles;


    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void validateInput() throws InValidInput {

        if(password.length()<8 || password.length()>16){
            throw new InValidInput("invalid password");
        }

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern patternEmail = Pattern.compile(regex);
        Matcher matcherEmail = patternEmail.matcher(email);
        if(!matcherEmail.matches()){
            throw new InValidInput("invalid email");
        }
        String regexName = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern patternName = Pattern.compile(regexName);
        Matcher matcherFName = patternName.matcher(firstName);

        if(matcherFName.matches()||firstName.length()<2 || firstName.length()>20){
            throw new InValidInput("invalid FirstName");
        }

        Matcher matcherLName = patternName.matcher(lastName);
        if(matcherLName.matches()||lastName.length()<2 || lastName.length()>20){
            throw new InValidInput("invalid LastName");
        }
        String regexNumber = "[0-9]+";
        Pattern patternNumber = Pattern.compile(regexNumber);
        Matcher matcherNumber = patternNumber.matcher(socialNumber);

        if(socialNumber.length()!=8||!matcherNumber.matches()){
            throw new InValidInput("invalid SocialNumber");
        }
    }
}
