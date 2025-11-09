package com.example.erp.backend.dtos.vendor_dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UpdateVendorDto {

    @Size(min = 3,max = 50,message = "Name must be between 3 and 50 characters.")
    private String name;

    @Size(min = 10,max=15,message = "Contact should be in between 10 to 15 number.")
    private String contactNo;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10,max=15,message = "Alter Contact should be in between 10 to 15 number.")
    private String alterContact;

    @Email(message = "Invalid email format")
    private String alterEmail;

    @Size(min=15,max = 15 ,message = "Gst Number Should be 15 digit.")
    private String gstNo;

    @Size(max=250,message = "Address should be less than 250 characters.")
    private String address;

    @Min(value = 1, message = "Invoice Create must be greater than 0")
    private Integer invoiceCreate;

    @Min(value = 1, message = "Credit Period must be greater than 0")
    private Integer creditPeriod;

    private Long agreementId;



}
