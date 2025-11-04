package com.example.erp.backend.entities;


import com.example.erp.backend.enums.ResumeType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "resources")
@Entity
public class Resource extends  DBCommon{

    @Column(name = "first_name")
    private  String firstName;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "personal_email",unique = true)
    private  String personalEmail;

    @Column(name="company_email",unique = true)
    private  String companyEmail;

    @Column(name = "mobile_Number",unique = true)
    private String mobileNumber;

    private String resume;

    @Column(name = "resume_type")
    @Enumerated(EnumType.STRING)
    private ResumeType resumeType;

    private String reference;

    @Column(name = "passing_year")
    private Integer passingYear;

    @OneToMany(mappedBy = "resource")
    @JsonManagedReference
    private List<ResourceSkill> skills;

    @Column(name = "joining_date")
    private LocalDate joiningDate=LocalDate.now();

    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private Boolean pf;

    @Column(name = "reason",columnDefinition = "TEXT")
    private  String reason;

    private Integer experience=0;

    @Column(name = "position_update",columnDefinition = "TEXT")
    private String positionUpdate;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private  Vendor vendor;





}
