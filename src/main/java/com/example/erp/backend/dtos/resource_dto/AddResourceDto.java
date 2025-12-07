package com.example.erp.backend.dtos.resource_dto;

import com.example.erp.backend.enums.ResumeType;
import com.example.erp.backend.validations.VaildResumeType;
import com.example.erp.backend.validations.ValidPassingYear;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddResourceDto {

    @NotBlank(message = "First Name is required.")
    @Size(min = 3,max=50,message = "First name must be between 3 and 50 characters.")
    private  String firstName;

    @NotBlank(message = "Last Name is required.")
    @Size(min = 3,max=50,message = "Last name must be between 3 and 50 characters.")
    private  String lastName;

    @NotBlank(message = "Personal Email is required.")
    @Email(message = "Invalid personal email.")
    private  String personalEmail;

    @Email(message = "Invalid company email.")// we can add regex
    private  String companyEmail;

    @NotBlank(message = "Mobile No. is required.")
    @Size(min = 10,max = 10,message = "Mobile No should be 10 digit.")
    private String mobileNumber;

    @VaildResumeType(message = "Resume type should be LINK or PATH. It is required.")
    private String resumeType;

    private String resume;

    private String reference;

    @NotEmpty(message = "At least one skill is required.")
    private List<Long> skills;

    @ValidPassingYear(message = "Passing year must be between  1947 and current year.")
    private Integer passingYear;

    private LocalDate joiningDate;

    private LocalDate contractEndDate;

    private Boolean pf;

    private Integer experience;

    private Long vendorId;
}
