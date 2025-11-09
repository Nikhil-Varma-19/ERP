package com.example.erp.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "vendors")
@Entity
@Getter
@Setter
public class Vendor extends  DBCommon{

    private String name;

    @Column(name="contact_no")
    private String contactNo;

    @Column
    private String email;

    @Column(name = "alter_contact")
    private String alterContact;

    @Column(name = "alter_email")
    private String alterEmail;

    @Column(name="gst_number",unique = true)
    private String gstNo;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "credit_period")
    private Integer creditPeriod;

   @Column(name = "invoice_create")
    private Integer invoiceCreate;

   @Column(name = "agreement_attachment")
    private  String agreementAttachment;

}
