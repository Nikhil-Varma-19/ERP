package com.example.erp.backend.dtos.vendor_dtos;

import lombok.Data;

@Data
public class VendorByIdDto extends GetAllVendorDto{
    private String agreementAttachment;
    private String alterEmail;
    private String alterContact;
}
