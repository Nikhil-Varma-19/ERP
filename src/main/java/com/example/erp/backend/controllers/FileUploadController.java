package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.FileUploadDto;
import com.example.erp.backend.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/file-upload")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(value = "/single",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileUploadDto>> singleFile(@RequestPart(value = "file") MultipartFile file){
        FileUploadDto result=fileUploadService.singleFile(file);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<List<FileUploadDto>>> multipleFile(@RequestPart(value = "files") MultipartFile[] files){
        List<FileUploadDto> result=fileUploadService.multipleFile(files);
        return  ResponseEntity.ok(ApiResponse.success(result));
    }



}
