package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.resource_dto.AddResourceDto;
import com.example.erp.backend.dtos.resource_dto.UpdateResourceDto;
import com.example.erp.backend.services.ResourceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/resource")
@Validated
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllResource(@RequestParam(defaultValue = "") String search,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(resourceService.getAllResource(search,page,size)));

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<String>> addResource(@RequestPart("data") @Valid AddResourceDto addResourceDto,
                                                           @RequestPart(value = "resume", required = false) MultipartFile resume) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(resourceService.addResource(addResourceDto, resume)));
    }

    @PatchMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<String>> updateResource(
            @PathVariable("id") @Min(1) Long id,
            @RequestPart("data") @Valid UpdateResourceDto updateResourceDto,
            @RequestPart(value = "resume", required = false) MultipartFile file
    ) {
        return ResponseEntity.ok(ApiResponse.success(resourceService.updateResource(id, updateResourceDto, file)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(resourceService.getById(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deleteResourceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.success(resourceService.deleteResource(id)));
    }

}
