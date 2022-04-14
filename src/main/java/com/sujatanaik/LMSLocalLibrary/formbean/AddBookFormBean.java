package com.sujatanaik.LMSLocalLibrary.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
public class AddBookFormBean {
    // These names must match the names in the form.

    // This id will be null in case of "create". It will be populated with the bookid in case of "edit".
    private Integer id;

    @NotBlank(message = "Please enter a title.")
    private String title;

//    @NotBlank(message = "Please enter a price.")
    @NotNull(message = "Please enter a positive number.")
    @Positive(message = "Please enter a positive number.")
    private Double price;

    @NotBlank(message = "Please select a condition.")
    private String condition;

    @NotBlank(message = "Please select a status.")
    private String status;

    @NotBlank(message = "Please enter an author.")
    private String author;

    @NotBlank(message = "Please enter a category.")
    private String category;

    private String imageURL;
}
