package com.example.oprs.model;

import java.util.List;

public class User {


    private Long id;

    private Long socialNumber;

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

    public Long getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(Long socialNumber) {
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

    public boolean isNotNull(){
        if(this.getPassword()!=null){
            return true;
        }
        if(this.getEmail()!=null){
            return true;
        }
        if(this.getFirstName()!=null){
            return true;
        }
        if(this.getLastName()!=null){
            return true;
        }
        if(this.getSocialNumber()!=null){
            return true;
        }else
            return false;
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

}
