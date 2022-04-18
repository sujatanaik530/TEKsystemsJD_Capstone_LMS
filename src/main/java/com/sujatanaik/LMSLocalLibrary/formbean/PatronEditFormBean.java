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
public class PatronEditFormBean {
    // These names must match the names in the form.

    // This id will be null in case of "create". It will be populated with the userid in case of "edit".
    private Integer id;

    private String email;

    private String fname;

    private String lname;

    @NotBlank (message = "Please enter a password.")
    //@Pattern(regexp = "[a-zA-Z]", message = "Password must have at least one alphabet")
    //@Pattern(regexp = "[0-9]", message = "Password must have at least one digit.")
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
    //@Length (min = 5, max = 5, message = "Zip code must be 5 digits.")
    @Pattern(regexp = "[0-9]{5}", message = "Please enter a valid zip code using 5 digits.")
    private String zip;

    @NotBlank (message = "Please enter a phone number.")
    @Pattern(regexp = "[0-9]{10}", message = "Please enter a valid phone number using 10 digits.")
    private String phone;

    private String ustatus;
}
