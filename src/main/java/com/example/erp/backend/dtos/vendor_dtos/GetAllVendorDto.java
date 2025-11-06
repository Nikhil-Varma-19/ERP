package com.example.erp.backend.dtos.vendor_dtos;

import lombok.Data;

@Data
public class GetAllVendorDto {
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private String gstNo;
    private Integer creditPeriod;
    private Integer invoiceCreate;

}
