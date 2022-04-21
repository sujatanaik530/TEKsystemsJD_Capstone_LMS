package com.sujatanaik.LMSLocalLibrary.formbean;

import com.sujatanaik.LMSLocalLibrary.validation.EmailUnique;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class PatronRegisterFormBean {
    // These names must match the names in the form.

    // This id will be null in case of "create". It will be populated with the userid in case of "edit".
    private Integer id;

    @NotBlank (message = "Please enter an email address.")
    @EmailUnique(message = "This email address already exists in database.")
    @Pattern (regexp = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", message = "Please enter a valid email address.")
    private String email;

    @NotBlank (message = "Please enter a first name.")
    private String fname;

    @NotBlank (message = "Please enter a last name.")
    private String lname;

    @NotBlank (message = "Please enter a password.")
    @Length(min = 3, max = 15, message = "Password must be between 3 and 15 characters in length.")
    private String password;

    @NotBlank (message = "Please confirm your password.")
    @Length (min = 3, max = 15)
    private String cpassword;

    @NotBlank (message = "Please enter your address.")
    private String aline1;

    private String aline2;

    @NotBlank (message = "Please enter a city.")
    private String city;

    @NotBlank (message = "Please select a state.")
    private String state;

    @NotBlank (message = "Please enter a zip code.")
    @Pattern(regexp = "[0-9]{5}", message = "Please enter a valid zip code using 5 digits.")
    private String zip;

    @NotBlank (message = "Please enter a phone number.")
    @Pattern(regexp = "[0-9]{10}", message = "Please enter a valid phone number using 10 digits.")
    private String phone;

    @NotBlank (message = "Please select a gender.")
    private String gender;

    private String news;

    private String ustatus;
}

// TODO password match validator (discord - spring-boot-resources) Steven 4/14
// TODO field match validator (discord - spring-boot-resources) Xu Huang 4/14
// TODO check OWASP site