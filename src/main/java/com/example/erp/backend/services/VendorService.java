package com.example.erp.backend.services;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.vendor_dtos.AddVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.GetAllVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.UpdateVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.VendorByIdDto;

public interface VendorService {
    String addVendor(AddVendorDto addVendorDto);

    PageResponseDto<GetAllVendorDto> getAllVendor(String search,int page,int size);

    VendorByIdDto getVendorById(Long id);

    String updateVendor(Long id, UpdateVendorDto updateVendorDto);

    String deleteVendor(Long id);
}
