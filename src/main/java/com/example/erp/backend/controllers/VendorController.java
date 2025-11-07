package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.vendor_dtos.AddVendorDto;
import com.example.erp.backend.services.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/v1/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ObjectMapper mapper;


    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllVendor(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(ApiResponse.success(vendorService.getAllVendor(search,page,size)));
    }


    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<?>> getVendorById(@PathVariable @Min(1) Long id){
            return ResponseEntity.ok(ApiResponse.success(vendorService.getVendorById(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  ResponseEntity<ApiResponse<String>>  addVendor(@RequestPart(value = "data",required = true) @Valid AddVendorDto addVendorDto, @RequestPart(value="file",required = false)MultipartFile file){
             return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(vendorService.addVendor(addVendorDto,file)));
    }

    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> updateVendor(@PathVariable("id") @Min(1) Long id,
    @RequestPart(value = "data") @Valid  AddVendorDto addVendorDto,
      @RequestPart(value="file",required = false)MultipartFile file){
        return ResponseEntity.ok(ApiResponse.success(vendorService.updateVendor(id,addVendorDto,file)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deleteVendor(@PathVariable("id") @Min(1) Long id){
        return ResponseEntity.ok(ApiResponse.success(vendorService.deleteVendor(id)));
    }
}
