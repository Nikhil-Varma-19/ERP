package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.vendor_dtos.AddVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.GetAllVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.VendorByIdDto;
import com.example.erp.backend.entities.Vendor;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.exceptions.NoData;
import com.example.erp.backend.repositories.VendorRep;
import com.example.erp.backend.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRep vendorRep;
    private final ModelMapper modelMapper;
    @Override
    public String addVendor(AddVendorDto addVendorDto, MultipartFile file) {
        System.out.println(addVendorDto);
        System.out.println(file);
        return "";
    }

    @Override
    public PageResponseDto<GetAllVendorDto> getAllVendor(String search, int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("id").descending());
        Page<Vendor> vendors=(search == null || search.isBlank()) ? vendorRep.findByIsActiveTrue(pageable)
                : vendorRep.searchActiveVendors(search,pageable);
        if(vendors.isEmpty()) throw new NoData("No Data");
        Page<GetAllVendorDto> mapVendor=vendors.map(vendor -> modelMapper.map(vendor, GetAllVendorDto.class));
        PageResponseDto<GetAllVendorDto> response=new PageResponseDto<>();
        response.setTotalRecords(mapVendor.getTotalElements());
        response.setTotalPages(mapVendor.getTotalPages());
        response.setResults(mapVendor.stream().toList());
        response.setCurrentPage(mapVendor.getNumber());
        return response;
    }

    @Override
    public VendorByIdDto getVendorById(Long id) {
        Optional<Vendor> vendor=vendorRep.findByIdAndIsActiveTrue(id);
        if(vendor.isEmpty()) throw new DataNotFound("Vendor is Not Exists.");
        return modelMapper.map(vendor.get(),VendorByIdDto.class);
    }

    @Override
    public String updateVendor(Long id, AddVendorDto addVendorDto, MultipartFile file) {
        return "";
    }

    @Override
    public String deleteVendor(Long id) {
        Optional<Vendor> vendor=vendorRep.findByIdAndIsActiveTrue(id);
        if(vendor.isEmpty()) throw new DataNotFound("Vendor is Not Exists.");
        vendor.get().setIsActive(false);
        vendorRep.save(vendor.get());
        return "Vendor is Delete Successfully.";
    }
}
