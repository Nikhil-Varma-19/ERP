package com.example.erp.backend.dtos.resource_dto;

import com.example.erp.backend.validations.VaildResumeType;
import com.example.erp.backend.validations.ValidPassingYear;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateResourceDto {
    @Size(min = 3,max=50,message = "First name must be between 3 and 50 characters.")
    private  String firstName;

    @Size(min = 3,max=50,message = "Last name must be between 3 and 50 characters.")
    private  String lastName;

    @Email(message = "Invalid personal email.")
    private  String personalEmail;

    @Email(message = "Invalid company email.")// we can add regex
    private  String companyEmail;

    @Size(min = 10,max = 10,message = "Mobile No should be 10 digit.")
    private String mobileNumber;

    private String resumeType;

    private String resume;

    private String reference;

    private List<Long> skills;

    private Integer passingYear;

    private LocalDate joiningDate;

    private LocalDate contractEndDate;

    private Boolean pf;

    private Integer experience;

    private Long vendorId;
}
