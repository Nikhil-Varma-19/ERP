package com.example.erp.backend.utilizs;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class FilePath {
    @Value("${dir.uploadFile}")
    private String uploadFile;

    @Value("${dir.getFile}")
    private String getFile;

    public String[] getPublicFile(){
        return new String[]{getFile+"/**"};
    }

    @PostConstruct
    public void init(){
        File folder =new File(uploadFile);
        if(!folder.exists()){
            boolean isPresent=folder.mkdirs();
            if(isPresent) log.info("Created Directory: {}",folder.getAbsolutePath());
            else log.warn("Failed to Created directory: {}",folder.getAbsolutePath());
        }else {
            log.info("Directory is already exists: {}",folder.getAbsolutePath());
        }
    }

    public String getUploadFile(){
        return uploadFile;
    }

    public String getGetFile(){
        return getFile;
    }

}
