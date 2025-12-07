package com.example.erp.backend.dtos.resource_dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AllResourceDTO {
    private Long id;
    private String vendorName;
    private Long vendorId;
    private String firstName;
    private String lastName;
    private String companyEmail;
    private String mobileNumber;
    private Integer passingYear;
    private List<String> technologies;
    private LocalDate joiningDate;
    private LocalDate contractEndDate;

}
