package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.vendor_dtos.AddVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.GetAllVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.UpdateVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.VendorByIdDto;
import com.example.erp.backend.entities.FileUpload;
import com.example.erp.backend.entities.Vendor;
import com.example.erp.backend.exceptions.AlreadyPresent;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.exceptions.NoData;
import com.example.erp.backend.repositories.VendorRep;
import com.example.erp.backend.services.FileUploadService;
import com.example.erp.backend.services.VendorService;
import com.example.erp.backend.utilizs.FilePath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRep vendorRep;
    private final ModelMapper modelMapper;
    private final FileUploadService fileUploadService;
    private final FilePath filePathGet;

    @Override
    @Transactional
    public String addVendor(AddVendorDto addVendorDto) {
        if(addVendorDto.getEmail() != null && vendorRep.existsByEmailAndIsActiveTrue(addVendorDto.getEmail())){
            throw  new AlreadyPresent("Email is already present.");
        }
        if(addVendorDto.getAlterEmail() != null && vendorRep.existsByAlterEmailAndIsActiveTrue(addVendorDto.getAlterEmail())){
            throw new AlreadyPresent("Alter Email is already present.");
        }
        if(addVendorDto.getGstNo() != null && vendorRep.existsByGstNoAndIsActiveTrue(addVendorDto.getGstNo())){
            throw  new AlreadyPresent("Gst No is already present.");
        }
        if(addVendorDto.getContactNo() !=null && vendorRep.existsByContactNoAndIsActiveTrue(addVendorDto.getContactNo())){
            throw new AlreadyPresent("Contact No is already present.");
        }
        if(addVendorDto.getAlterContact() != null && vendorRep.existsByAlterContactAndIsActiveTrue(addVendorDto.getAlterContact())){
            throw new AlreadyPresent("Alter Contact No is already present.");
        }
        Long agreementId=null;
        if(addVendorDto.getAgreementId() != null){
            agreementId=addVendorDto.getAgreementId();
            addVendorDto.setAgreementId(null);
        }
        Vendor vendor = modelMapper.map(addVendorDto, Vendor.class);
        if(agreementId != null){
            FileUpload getFileUpload = fileUploadService.getById(agreementId);
            String newFileName = UUID.randomUUID() + "_" + getFileUpload.getFileName();
            String vendorFolder = filePathGet.getUploadFile() + "/vendors";
            String vendorNewFileStorage = vendorFolder + "/" + newFileName;
            String getVendorFileStorage = filePathGet.getGetFile() + "/vendors/" + newFileName;
            vendor.setAgreementAttachment(getVendorFileStorage);
            Vendor savedVendor=vendorRep.save(vendor);
            if(savedVendor.getId() != null ){
                fileUploadService.savedDelete(vendorNewFileStorage, getFileUpload);
                fileUploadService.createFolderAndMoveFile(getFileUpload.getFileUpload(),vendorNewFileStorage,vendorFolder);
            }
        }else {
            vendorRep.save(vendor);
        }

            return "Vendor created Successfully";
    }

    @Override
    public PageResponseDto<GetAllVendorDto> getAllVendor(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Vendor> vendors = (search == null || search.isBlank()) ? vendorRep.findByIsActiveTrue(pageable)
                : vendorRep.searchActiveVendors(search, pageable);
        if (vendors.isEmpty()) throw new NoData("No Data");
        Page<GetAllVendorDto> mapVendor = vendors.map(vendor -> modelMapper.map(vendor, GetAllVendorDto.class));
        PageResponseDto<GetAllVendorDto> response = new PageResponseDto<>();
        response.setTotalRecords(mapVendor.getTotalElements());
        response.setTotalPages(mapVendor.getTotalPages());
        response.setResults(mapVendor.stream().toList());
        response.setCurrentPage(mapVendor.getNumber());
        return response;
    }

    @Override
    public VendorByIdDto getVendorById(Long id) {
        Optional<Vendor> vendor = vendorRep.findByIdAndIsActiveTrue(id);
        if (vendor.isEmpty()) throw new DataNotFound("Vendor not exists.");
        return modelMapper.map(vendor.get(), VendorByIdDto.class);
    }

    @Override
    public String updateVendor(Long id, UpdateVendorDto updateVendorDto) {
        Vendor vendor=vendorRep.findByIdAndIsActiveTrue(id).orElseThrow(()-> new DataNotFound("Vendor not exists  "));
        if(updateVendorDto.getEmail() != null && vendorRep.existsByEmailAndIsActiveTrue(updateVendorDto.getEmail())){
            throw  new AlreadyPresent("Email is already present.");
        }
        if(updateVendorDto.getAlterEmail() != null && vendorRep.existsByAlterEmailAndIsActiveTrue(updateVendorDto.getAlterEmail())){
            throw new AlreadyPresent("Alter Email is already present.");
        }
        if(updateVendorDto.getGstNo() != null && vendorRep.existsByGstNoAndIsActiveTrue(updateVendorDto.getGstNo())){
            throw  new AlreadyPresent("Gst No is already present.");
        }
        if(updateVendorDto.getContactNo() !=null && vendorRep.existsByContactNoAndIsActiveTrue(updateVendorDto.getContactNo())){
            throw new AlreadyPresent("Contact No is already present.");
        }
        if(updateVendorDto.getAlterContact() != null && vendorRep.existsByAlterContactAndIsActiveTrue(updateVendorDto.getAlterContact())){
            throw new AlreadyPresent("Alter Contact No is already present.");
        }
       modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
       modelMapper.map(updateVendorDto,vendor);
       if(updateVendorDto.getAgreementId() != null){
           FileUpload getFileUpload = fileUploadService.getById(updateVendorDto.getAgreementId());
           String newFileName = UUID.randomUUID() + "_" + getFileUpload.getFileName();
           String vendorFolder = filePathGet.getUploadFile() + "/vendors";
           String vendorNewFileStorage = vendorFolder + "/" + newFileName;
           String getVendorFileStorage = filePathGet.getGetFile() + "/vendors/" + newFileName;
           vendor.setAgreementAttachment(getVendorFileStorage);
           Vendor savedVendor=vendorRep.save(vendor);
           if(savedVendor.getId() != null ){
               fileUploadService.savedDelete(vendorNewFileStorage, getFileUpload);
               fileUploadService.createFolderAndMoveFile(getFileUpload.getFileUpload(),vendorNewFileStorage,vendorFolder);
           }
       }else {
            vendorRep.save(vendor);
       }
        return "Vendor Updated Successfully";
    }

    @Override
    public String deleteVendor(Long id) {
        Optional<Vendor> vendor = vendorRep.findByIdAndIsActiveTrue(id);
        if (vendor.isEmpty()) throw new DataNotFound("Vendor is Not Exists.");
        vendor.get().setIsActive(false);
        vendorRep.save(vendor.get());
        return "Vendor is Delete Successfully.";
    }
}
