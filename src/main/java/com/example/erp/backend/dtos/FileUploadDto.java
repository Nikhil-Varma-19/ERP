package com.example.erp.backend.dtos;

import lombok.Data;

@Data
public class FileUploadDto {
    private Long id;
//    private String filePath;
    private String fileUpload;
    private String fileName;
}
