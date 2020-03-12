package com.example.oprs.dto;

import com.example.oprs.annotation.EmptyOrByRegex;
import com.example.oprs.enums.Purpose;
import com.example.oprs.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class ApplicationInfoDto {
    private long id;
    @Pattern(regexp = "[A-Z][a-z]*", message = "First Name must have one capital letter and then a small letters")
    @Size(min = 3, max = 50, message = "Name characters size must be between 3 and 50")
    @NotNull(message = "First Name can't be null")
    private String firstName;
    @Pattern(regexp = "[A-Z][a-z]*", message = "Last Name must have one capital letter and then a small letters")
    @Size(min = 3, max = 50, message = "Last Name characters size must be between 3 and 50")
    @NotNull(message = "Last Name can't be null")
    private String lastName;
    @NotNull(message = "Gender can't be null")
    private Boolean gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birth date can't be null")
    private Date dateOfBirth;
    @NotNull(message = "Country can't be null")
    private String countryOfBirth;
    @Pattern(regexp = "\\d{9}", message = "Social Security Number size must be 9 And only numbers")
    @NotNull(message = "Social Security Number can't be null")
    private String socialSecurityNumber;
    @NotNull(message = "address can't be null")
    private String address;
    @Email
    @NotNull(message = "Email can't be null")
    private String email;
    @Pattern(regexp = "\\d{9}", message = "Phone Number size must be 9 And only numbers")
    @NotNull(message = "Phone Number can't be null")
    private String phoneNumber;
    @Pattern(regexp = "\\d{4}", message = "Post Code size must be 4 And only numbers")
    @NotNull(message = "Post Code can't be null")
    private String postCode;
    @EmptyOrByRegex(regexp ="[a-z]{2}\\d{7}" , message = "passport number must be like this ( af0983695 ) two letters and then seven numbers")
    private String oldPassportNumber;
    @EmptyOrByRegex(regexp ="[a-z]{2}\\d{7}" , message = "passport number must be like this ( af0983695 ) two letters and then seven numbers")
    private String lostPassportNumber;
    @EmptyOrByRegex(regexp ="\\d{3}" , message = "(From Whom) size must be 3 And only numbers\"")
    private String fromWhom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date givenDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    @NotNull(message = "purpose can't be null")
    private Purpose purpose;
    private String photoUrl;
    private Status status;
    private String token;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getLostPassportNumber() {
        return lostPassportNumber;
    }

    public void setLostPassportNumber(String lostPassportNumber) {
        this.lostPassportNumber = lostPassportNumber;
    }

    public String getOldPassportNumber() {
        return oldPassportNumber;
    }

    public void setOldPassportNumber(String oldPassportNumber) {
        this.oldPassportNumber = oldPassportNumber;
    }

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public Date getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
