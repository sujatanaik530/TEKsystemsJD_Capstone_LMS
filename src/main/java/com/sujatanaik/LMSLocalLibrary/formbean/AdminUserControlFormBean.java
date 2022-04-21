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
public class AdminUserControlFormBean {
    // These names must match the names in the form.

    // This id will be null in case of "create". It will be populated with the userid in case of "edit".
    private Integer id;

    private String email;

    private String fname;

    private String lname;

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
