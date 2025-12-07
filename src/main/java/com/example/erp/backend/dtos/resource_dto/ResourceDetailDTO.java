package com.example.erp.backend.dtos.resource_dto;

import com.example.erp.backend.enums.ResumeType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ResourceDetailDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalEmail;
    private String companyEmail;
    private String mobileNumber;
    private String resume;
    private ResumeType resumeType;
    private String reference;
    private Integer passingYear;
    private LocalDate joiningDate;
    private LocalDate contractEndDate;
    private LocalDate endDate;
    private Boolean pf;
    private String reason;
    private Integer experience;
    private String positionUpdate;
    private String vendorName;
    private List<String> technologies;
}
