package com.example.erp.backend.services;

import com.example.erp.backend.dtos.FileUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    List<FileUploadDto> multipleFile(MultipartFile[] files);

    FileUploadDto singleFile(MultipartFile file) ;
}
