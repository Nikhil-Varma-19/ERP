package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.vendor_dtos.AddVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.UpdateVendorDto;
import com.example.erp.backend.services.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/vendor")
@Validated
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

    @PostMapping
    public  ResponseEntity<ApiResponse<String>>  addVendor(@RequestBody @Valid AddVendorDto addVendorDto){
             return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(vendorService.addVendor(addVendorDto)));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<?>> updateVendor(@PathVariable(value = "id",required = true) @Min(1)  Long id, @RequestBody @Valid UpdateVendorDto updateVendorDto){
        return ResponseEntity.ok(ApiResponse.success(vendorService.updateVendor(id,updateVendorDto)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deleteVendor(@PathVariable("id") @Min(1) Long id){
        return ResponseEntity.ok(ApiResponse.success(vendorService.deleteVendor(id)));
    }
}
