package com.example.erp.backend.dtos.vendor_dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddVendorDto {
    @NotBlank(message = "Name is Required.")
    @Size(min = 3,max = 50,message = "Name must be between 3 and 50 characters.")
    private String name;

    @NotBlank(message = "Contact No is required.")
    private String contactNo;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format")
    private String email;

    private String alterContact;

    @Email(message = "Invalid email format")
    private String alterEmail;

    private String gstNo;

    @Size(max=250,message = "Address should be less than 250 characters.")
    private String address;

    @Min(value = 1, message = "Invoice Create must be greater than 0")
    private Integer invoiceCreate;

    @Min(value = 1, message = "Credit Period must be greater than 0")
    private Integer creditPeriod;


}
