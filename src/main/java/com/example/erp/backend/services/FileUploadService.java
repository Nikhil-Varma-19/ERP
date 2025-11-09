package com.example.erp.backend.services;

import com.example.erp.backend.dtos.FileUploadDto;
import com.example.erp.backend.entities.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    List<FileUploadDto> multipleFile(MultipartFile[] files);

    FileUploadDto singleFile(MultipartFile file) ;

    FileUpload getById(Long id);

    void savedDelete(String path,FileUpload fileUpload);

    void createFolderAndMoveFile(String source,String target,String dir);
}
