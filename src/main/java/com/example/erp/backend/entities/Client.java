package com.example.erp.backend.entities;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "clients")
@Entity
public class Client extends DBCommon{

    private String name;

    private String url;

    @Column(columnDefinition = "TEXT")
    private  String address;

    @Column(name = "agreement_sign")
    private  String agreementSign;

    @Column(name = "weekend_working")
    private  String weekendWorking;

    @Column(name = "invoice_create")
    private Integer invoiceCreate;

    @Column(name = "credit_period")
    private Integer creditPeriod;

    @Column(name="gst_number",unique = true)
    private String gstNo;

    @Column(name = "billing_address",columnDefinition = "TEXT")
    private String billingAddress;

    private String nationality;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean product;

    @Column(name = "paid_leaves")
    private Boolean paidLeaves;

    @Column(name = "client_request")
    private Boolean clientRequest;

    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<ClientContact> clientContact;
}
