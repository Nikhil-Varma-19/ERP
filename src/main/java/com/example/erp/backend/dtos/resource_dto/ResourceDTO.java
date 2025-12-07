package com.example.erp.backend.dtos.resource_dto;

import com.example.erp.backend.dtos.TechnologyDTO;
import com.example.erp.backend.dtos.vendor_dtos.VendorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String companyEmail;
    private List<TechnologyDTO> technologies;
    private VendorDTO vendor;

    // Additional fields (Only in complete view)
    private String personalEmail;
    private String resume;
    private String resumeType;
    private String reference;
    private Integer passingYear;
    private LocalDate joiningDate;
    private LocalDate contractEndDate;
    private LocalDate endDate;
    private Boolean pf;
    private String reason;
    private Integer experience;
    private String positionUpdate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
}
