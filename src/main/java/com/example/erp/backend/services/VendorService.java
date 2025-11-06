package com.example.erp.backend.services;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.vendor_dtos.AddVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.GetAllVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.VendorByIdDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.multipart.MultipartFile;

public interface VendorService {
    String addVendor(AddVendorDto addVendorDto, MultipartFile file);

    PageResponseDto<GetAllVendorDto> getAllVendor(String search,int page,int size);

    VendorByIdDto getVendorById(Long id);

    String updateVendor( Long id,  AddVendorDto addVendorDto, MultipartFile file);

    String deleteVendor(Long id);
}
